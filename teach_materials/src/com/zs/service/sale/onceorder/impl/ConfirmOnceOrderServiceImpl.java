package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderForMaxCodeDAO;
import com.zs.dao.sale.onceorderlog.OnceOrderLogDAO;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMByOrderIdDAO;
import com.zs.dao.sale.onceordertm.FindTMPriceByStudentCodeForConfirmOnceOrderDAO;
import com.zs.dao.sale.studentbookorder.DelStudentBookOrderByStudentCodeForUnConfirmDAO;
import com.zs.dao.sale.studentbookorderlog.DelStudentBookOrderLogByStudentCodeForUnConfirmDAO;
import com.zs.dao.sale.studentbookordertm.DelStudentBookOrderTMByStudentCodeForUnConfirmDAO;
import com.zs.dao.sale.studentbookordertm.FindTMPriceByStudentCodeForConfirmOrderDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.sale.*;
import com.zs.domain.sync.Student;
import com.zs.service.finance.studentexpense.FindStuEPageByWhereService;
import com.zs.service.sale.onceorder.ConfirmOnceOrderService;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学习中心确认订单，如果订单明细里面的教材库存不足，就不能确认，状态设为库存不足
 * Created by Allen on 2015/7/8.
 */
@Service("confirmOnceOrderService")
public class ConfirmOnceOrderServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, FindOnceOrderByCodeDAO>
        implements ConfirmOnceOrderService {

    @Resource
    private FindOnceOrderTMByOrderIdDAO findOnceOrderTMByOrderIdDAO;
    @Resource
    private OnceOrderLogDAO onceOrderLogDAO;
    @Resource
    private FindStuEPageByWhereService findStuEPageByWhereService;
    @Resource
    private FindTMPriceByStudentCodeForConfirmOrderDAO findTMPriceByStudentCodeForConfirmOrderDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindStudentBookOnceOrderForMaxCodeDAO findStudentBookOnceOrderForMaxCodeDAO;
    @Resource
    private DelStudentBookOrderTMByStudentCodeForUnConfirmDAO delStudentBookOrderTMByStudentCodeForUnConfirmDAO;
    @Resource
    private DelStudentBookOrderLogByStudentCodeForUnConfirmDAO delStudentBookOrderLogByStudentCodeForUnConfirmDAO;
    @Resource
    private DelStudentBookOrderByStudentCodeForUnConfirmDAO delStudentBookOrderByStudentCodeForUnConfirmDAO;
    @Resource
    private FindTMPriceByStudentCodeForConfirmOnceOrderDAO findTMPriceByStudentCodeForConfirmOnceOrderDAO;

    @Override
    @Transactional
    public synchronized void confirmOnceOrder(long id, String spotCode, String loginName) throws Exception {
        //订单总金额
        float totalPrice = 0;
        //查询订单信息
        StudentBookOnceOrder studentBookOnceOrder = super.get(id);
        if(null == studentBookOnceOrder){
            throw new BusinessException("没有找到订单信息");
        }
        if(studentBookOnceOrder.getState() > StudentBookOnceOrder.STATE_UNCONFIRMED){
            throw new BusinessException("学号："+studentBookOnceOrder.getStudentCode()+"，订单号："+studentBookOnceOrder.getOrderCode()+", 状态不能确认！");
        }
        //查询订单明细
        double orderPrice = 0;
        List<StudentBookOnceOrderTM> studentBookOnceOrderTMList = findOnceOrderTMByOrderIdDAO.find(studentBookOnceOrder.getId());
        if(null == studentBookOnceOrderTMList || 1 > studentBookOnceOrderTMList.size()){
            throw new BusinessException("没有找到该订单的明细");
        }else {
            boolean isCleanZore = true;
            for(StudentBookOnceOrderTM studentBookOnceOrderTM : studentBookOnceOrderTMList){
                if(1 < studentBookOnceOrderTM.getCount()){
                    throw new BusinessException("订单号："+studentBookOnceOrder.getOrderCode()+", 教材明细数量不能大于1，不能进行确认！");
                }
                if(null != studentBookOnceOrderTM.getCount() && 0 < studentBookOnceOrderTM.getCount()){
                    isCleanZore = false;
                }
                orderPrice += studentBookOnceOrderTM.getCount()*studentBookOnceOrderTM.getPrice();
            }
            if(isCleanZore){
                throw new BusinessException("学号："+studentBookOnceOrder.getStudentCode()+", 教材明细数量全部为0，不能进行确认！");
            }
        }

        //查询学生的的欠费情况，大于1000，就不能确认订单
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("studentCode", studentBookOnceOrder.getStudentCode());
        JSONObject jsonObject = findStuEPageByWhereService.findPageByWhere(new PageInfo(), paramsMap, null);
        float pay = 0;
        float buy = 0;
        if(null != jsonObject && null != jsonObject.get("pageInfo")){
            JSONObject jsonObject2 = (JSONObject)jsonObject.get("pageInfo");
            if(null != jsonObject2 && null != jsonObject2.get("pageResults")) {
                JSONArray jsonArray = (JSONArray) jsonObject2.get("pageResults");
                if(0 < jsonArray.size()) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                    pay = null == jsonObject1.get("pay") ? 0 : Float.valueOf(jsonObject1.get("pay").toString());
                    buy = null == jsonObject1.get("buy") ? 0 : Float.valueOf(jsonObject1.get("buy").toString());
                }
            }
        }
        //统计该学生已经确认了，但是还没发书的订单价格。
        double confirmOrderPrice = findTMPriceByStudentCodeForConfirmOrderDAO.find(studentBookOnceOrder.getStudentCode());
        double confirmOnceOrderPrice = findTMPriceByStudentCodeForConfirmOnceOrderDAO.find(studentBookOnceOrder.getStudentCode());
        if (2000 < totalPrice + buy + confirmOrderPrice + confirmOnceOrderPrice + orderPrice - pay) {
            throw new BusinessException("学号：" + studentBookOnceOrder.getStudentCode() + "，欠款超过2000，不能确认订单！");
        }

        //获取当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();
        //得到当前学期最大的订单号
        int num = 0;
        StudentBookOnceOrder maxCodeOrder = findStudentBookOnceOrderForMaxCodeDAO.find(semester.getId());
        if(null != maxCodeOrder){
            String maxOrderCode = maxCodeOrder.getOrderCode();
            System.out.println("maxOrderCode: -------------  "+maxOrderCode);
            num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length()-6, maxOrderCode.length()));
        }
        System.out.println("num: -------------  "+num);
        //生成学生订单号
        String orderCode = OrderCodeTools.createStudentOnceOrderCodeForConfirm(semester.getYear(), semester.getQuarter(), num + 1);
        System.out.println("orderCode: -------------  "+orderCode);

        studentBookOnceOrder.setSemesterId(semester.getId());
        studentBookOnceOrder.setOrderCode(orderCode);
        studentBookOnceOrder.setOperator(loginName);
        studentBookOnceOrder.setOperateTime(DateTools.getLongNowTime());
        studentBookOnceOrder.setState(StudentBookOrder.STATE_CONFIRMED);
        //修改数据
        super.update(studentBookOnceOrder);
        //记录订单状态变更日志
        StudentBookOnceOrderLog studentBookOnceOrderLog = new StudentBookOnceOrderLog();
        studentBookOnceOrderLog.setOrderId(studentBookOnceOrder.getId());
        studentBookOnceOrderLog.setState(studentBookOnceOrder.getState());
        studentBookOnceOrderLog.setOperator(loginName);
        onceOrderLogDAO.save(studentBookOnceOrderLog);
        //修改学生为订购一次性订单
        Student student = findStudentByCodeDAO.getStudentByCode(studentBookOnceOrder.getStudentCode());
        student.setIsOnceOrder(Student.IS_ONCE_ORDER_YES);
        findStudentByCodeDAO.update(student);
        //查看学生还有没有未确认的学生订单，有就删除
        delStudentBookOrderTMByStudentCodeForUnConfirmDAO.del(studentBookOnceOrder.getStudentCode());
        delStudentBookOrderLogByStudentCodeForUnConfirmDAO.del(studentBookOnceOrder.getStudentCode());
        delStudentBookOrderByStudentCodeForUnConfirmDAO.del(studentBookOnceOrder.getStudentCode());
    }

    @Override
    @Transactional
    public void confirmOnceOrderForMore(String ids, String spotCode, String loginName) throws Exception {
        if(StringUtils.isEmpty(ids)){
            throw new BusinessException("没有传入订单id");
        }
        String[] idsArray = ids.split(",");
        for(String id : idsArray){
            this.confirmOnceOrder(Long.parseLong(id), spotCode, loginName);
        }
    }
}
