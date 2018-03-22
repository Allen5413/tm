package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotteachmaterialstock.FindStockByTeachMaterialIdAndSpotCodeDao;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.finance.studentexpense.FindTotalPayBuyByStudentCodeDAO;
import com.zs.dao.placeorder.FindTMCountForConfirmPlaceOrderDAO;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderByStudentCodeForUnConfirmDAO;
import com.zs.dao.sale.onceordertm.EditOnceOrderTMForIsBuyDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByCodeDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderForMaxCodeDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.dao.sale.studentbookorderlog.StudentBookOrderLogDAO;
import com.zs.dao.sale.studentbookordertm.BatchStudentBookOrderTMDAO;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByOrderCodeDAO;
import com.zs.dao.sale.studentbookordertm.FindTMCountForConfirmOrderDAO;
import com.zs.dao.sale.studentbookordertm.FindTMPriceByStudentCodeForConfirmOrderDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.basic.SpotTeachMaterialStock;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderLog;
import com.zs.domain.sale.StudentBookOrderTM;
import com.zs.domain.sync.Student;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.finance.studentexpense.FindStuEPageByWhereService;
import com.zs.service.sale.studentbookorder.ConfirmStudentBookOrderService;
import com.zs.tools.DateTools;
import com.zs.tools.OrderCodeTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学习中心确认订单，如果订单明细里面的教材库存不足，就不能确认，状态设为库存不足
 * Created by Allen on 2015/7/8.
 */
@Service("confirmStudentBookOrderService")
public class ConfirmStudentBookOrderServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO>
        implements ConfirmStudentBookOrderService {

    @Resource
    private FindStudentBookOrderTMByOrderCodeDAO findStudentBookOrderTMByOrderCodeDAO;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;
    @Resource
    private FindStockByTeachMaterialIdAndSpotCodeDao findStockByTeachMaterialIdAndSpotCodeDao;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private StudentBookOrderLogDAO studentBookOrderLogDAO;
    @Resource
    private FindTMCountForConfirmOrderDAO findTMCountForConfirmOrderDAO;
    @Resource
    private FindTMCountForConfirmPlaceOrderDAO findTMCountForConfirmPlaceOrderDAO;
    @Resource
    private FindStuEPageByWhereService findStuEPageByWhereService;
    @Resource
    private FindStudentBookOrderByCodeDAO findStudentBookOrderByCodeDAO;
    @Resource
    private FindTotalPayBuyByStudentCodeDAO findTotalPayBuyByStudentCodeDAO;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindStudentBookOrderForMaxCodeDAO findStudentBookOrderForMaxCodeDAO;
    @Resource
    private BatchStudentBookOrderTMDAO batchStudentBookOrderTMDAO;
    @Resource
    private FindTMPriceByStudentCodeForConfirmOrderDAO findTMPriceByStudentCodeForConfirmOrderDAO;
    @Resource
    private FindStudentBookOnceOrderByStudentCodeForUnConfirmDAO findStudentBookOnceOrderByStudentCodeForUnConfirmDAO;
    @Resource
    private EditOnceOrderTMForIsBuyDAO editOnceOrderTMForIsBuyDAO;

    //记录已经计算过的教材渠道数量，还没提交数据时，要把之前确认的数量也减除
    private Map<Long, Map<Long, Integer>> tmStockMap = new HashMap<Long, Map<Long, Integer>>();

    @Override
    @Transactional
    public void confirmStudentBookOrder(long id, String spotCode, String loginName) throws Exception {
        //库存是否够
        boolean isStock = true;
        //订单总金额
        float totalPrice = 0;
        //获取当前学期
        Semester semester = findNowSemesterService.getNowSemester();
        //查询订单信息
        StudentBookOrder studentBookOrder = super.get(id);
        if(null == studentBookOrder || StringUtils.isEmpty(studentBookOrder.getOrderCode())){
            throw new BusinessException("没有找到订单信息");
        }
        if(studentBookOrder.getState() > StudentBookOrder.STATE_UNCONFIRMED){
            throw new BusinessException("订单号："+studentBookOrder.getOrderCode()+", 状态不能确认！");
        }
        //查询订单明细
        double orderPrice = 0;
        List<StudentBookOrderTM> studentBookOrderTMList = findStudentBookOrderTMByOrderCodeDAO.findStudentBookOrderTMByOrderCode(studentBookOrder.getOrderCode());
        if(null == studentBookOrderTMList || 1 > studentBookOrderTMList.size()){
            throw new BusinessException("没有找到该订单的明细");
        }else {
            boolean isCleanZore = true;
            for(StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList){
                if(1 < studentBookOrderTM.getCount()){
                    throw new BusinessException("订单号："+studentBookOrder.getOrderCode()+", 教材明细数量不能大于1，不能进行确认！");
                }
                if(null != studentBookOrderTM.getCount() && 0 < studentBookOrderTM.getCount()){
                    isCleanZore = false;
                }
                orderPrice += studentBookOrderTM.getCount()*studentBookOrderTM.getPrice();
            }
            if(isCleanZore){
                throw new BusinessException("订单号："+studentBookOrder.getOrderCode()+", 教材明细数量全部为0，不能进行确认！");
            }
        }

        //是否是学习中心订单，是的话，教材库存获取学习中心的库存；不是就获取渠道库存
        if(studentBookOrder.getIsSpotOrder() == StudentBookOrder.ISSPOTORDER_YES){
            //如果没有传学习中心编号，就去获取订单中学生的所属学习中心编号
            if(StringUtils.isEmpty(spotCode)){
                //查询学生信息
                Student student = findStudentByCodeDAO.getStudentByCode(studentBookOrder.getStudentCode());
                spotCode = student.getSpotCode();
            }
            if(StringUtils.isEmpty(spotCode)){
                throw new BusinessException("没有找到学习中心信息");
            }
            //遍历明细，判断库存是否够
            for(StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList){
                SpotTeachMaterialStock spotTeachMaterialStock = findStockByTeachMaterialIdAndSpotCodeDao.getStockByTeachMaterialIdAndSpotCode(studentBookOrderTM.getTeachMaterialId(), spotCode);
                if(null == spotTeachMaterialStock || 1 > spotTeachMaterialStock.getStock()){
                    isStock = false;
                    break;
                }
                totalPrice += studentBookOrderTM.getPrice() * studentBookOrderTM.getCount();
            }
        }else{
            //遍历明细，判断库存是否够
            for(StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList){
                TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId());
                //查询当前学期学生订单该渠道下的教材已经确认未发货的数量
                BigDecimal bigDecimal = findTMCountForConfirmOrderDAO.findTMCountForConfirmOrder(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId(), semester.getId());
                //查询当前学期预订单该渠道下的教材已生成未发货的数量
                BigDecimal bigDecimal2 = findTMCountForConfirmPlaceOrderDAO.findTMCountForConfirmPlaceOrder(studentBookOrderTM.getTeachMaterialId(), studentBookOrder.getIssueChannelId(), semester.getId());
                int confirmCount = null == bigDecimal ? 0 : bigDecimal.intValue();
                int confirmCount2 = null == bigDecimal2 ? 0 : bigDecimal2.intValue();
                //查看之前是否有已经确认的数量
                int count = 0;
                if(null != tmStockMap && 0 < tmStockMap.size()){
                    Map<Long, Integer> map = tmStockMap.get(studentBookOrderTM.getTeachMaterialId());
                    count = null == map ? 0 : map.get(studentBookOrder.getIssueChannelId());
                }
                if((null == teachMaterialStock && 0 < count + confirmCount + confirmCount2) || (null != teachMaterialStock && 0 > (teachMaterialStock.getStock() - confirmCount - confirmCount2 - count))){
                    isStock = false;
                    break;
                }
                totalPrice += studentBookOrderTM.getPrice() * studentBookOrderTM.getCount();
            }
        }
        //查询学生的的欠费情况，大于500，就不能确认订单
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("studentCode", studentBookOrder.getStudentCode());
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
        double confirmOrderPrice = findTMPriceByStudentCodeForConfirmOrderDAO.find(studentBookOrder.getStudentCode());
        if (2000 < totalPrice + buy + confirmOrderPrice + orderPrice - pay) {
            throw new BusinessException("学号：" + studentBookOrder.getStudentCode() + "，欠款超过2000，不能确认订单！");
        }
        if(isStock){
            studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
            //库存够的订单，把教材的库存放入map，以便后面的订单好计算库存
            for(StudentBookOrderTM studentBookOrderTM : studentBookOrderTMList){
                Map<Long, Integer> map = new HashMap<Long, Integer>();
                map.put(studentBookOrder.getIssueChannelId(), studentBookOrderTM.getCount());
                tmStockMap.put(studentBookOrderTM.getTeachMaterialId(), map);
            }
        }else{
            //studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_NOT);
            studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
        }

        //查询该学生的一次性订单
        List<StudentBookOnceOrder> studentBookOnceOrderList = findStudentBookOnceOrderByStudentCodeForUnConfirmDAO.find(studentBookOrder.getStudentCode());
        if(null != studentBookOnceOrderList){
            for(StudentBookOnceOrder studentBookOnceOrder : studentBookOnceOrderList) {
                if (StudentBookOnceOrder.STATE_UNCONFIRMED < studentBookOnceOrder.getState()) {
                    throw new BusinessException("该学生已经确认过一次性订单，学生普通订单不能再进行确认");
                }
            }
            //把确认了的数量大于0的课程，修改为已经购买过
            editOnceOrderTMForIsBuyDAO.editor(studentBookOrder.getOrderCode());
        }

        studentBookOrder.setOperator(loginName);
        studentBookOrder.setOperateTime(DateTools.getLongNowTime());
        studentBookOrder.setState(StudentBookOrder.STATE_CONFIRMED);
        //修改数据
        super.update(studentBookOrder);
        //记录订单状态变更日志
        StudentBookOrderLog studentBookOrderLog = new StudentBookOrderLog();
        studentBookOrderLog.setOrderCode(studentBookOrder.getOrderCode());
        studentBookOrderLog.setState(studentBookOrder.getState());
        studentBookOrderLog.setOperator(loginName);
        studentBookOrderLogDAO.save(studentBookOrderLog);


    }

    @Override
    @Transactional
    public void confirmStudentBookOrderForMore(String ids, String spotCode, String loginName) throws Exception {
        if(StringUtils.isEmpty(ids)){
            throw new BusinessException("没有传入订单id");
        }
        String[] idsArray = ids.split(",");
        for(String id : idsArray){
            this.confirmStudentBookOrder(Long.parseLong(id), spotCode, loginName);
        }
    }

    @Override
    @Transactional
    public JSONObject confirmStudentBookOrderForStudent(String studentCode, String[] idCodeCountArray, String loginName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Student student = findStudentByCodeDAO.getStudentByCode(studentCode);
        if(null == student){
            throw new BusinessException("没有找到学号: "+studentCode+"的信息");
        }

        //查询学生的余额是否够确认订单
        double pay = 0, buy = 0, acc = 0;
        Object[] objects = findTotalPayBuyByStudentCodeDAO.find(studentCode);
        if(null != objects && 0 < objects.length){
            pay = Double.parseDouble(objects[0].toString());
            buy = Double.parseDouble(objects[1].toString());
            acc = new BigDecimal(pay).subtract(new BigDecimal(buy)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        //统计该学生已经确认了，但是还没发书的订单价格。
        double confirmOrderPrice = findTMPriceByStudentCodeForConfirmOrderDAO.find(studentCode);

        //需要合并的订单明细
        List<StudentBookOrderTM> mergeStudentBookOrderTMList = new ArrayList<StudentBookOrderTM>();
        //需要修改数量的订单明细
        List<StudentBookOrderTM> editStudentBookOrderTMList = new ArrayList<StudentBookOrderTM>();
        //需要修改状态的订单信息
        List<StudentBookOrder> editStudentBookOrderList = new ArrayList<StudentBookOrder>();
        //查询该订单下的教材明细
        List<StudentBookOrderTM> studentBookOrderTMList = null;
        StudentBookOrder studentBookOrder = null;
        String beforeOrderCode = "";
        double confirmOrderTotalPrice = 0;
        for(String idCodeCountStr : idCodeCountArray){
            String[] idCodeCount = idCodeCountStr.split("_");
            String orderCode = idCodeCount[1];
            if(!beforeOrderCode.equals(orderCode)) {
                //查询订单信息
                studentBookOrder = findStudentBookOrderByCodeDAO.findStudentBookOrderByCode(orderCode);
                if(null == studentBookOrder){
                    throw new BusinessException("订单号："+orderCode+", 没有找到订单信息");
                }
                //查询该订单下的教材明细
                studentBookOrderTMList = findStudentBookOrderTMByOrderCodeDAO.findStudentBookOrderTMByOrderCode(orderCode);
                mergeStudentBookOrderTMList.addAll(studentBookOrderTMList);
                editStudentBookOrderList.add(studentBookOrder);
            }
            beforeOrderCode = orderCode;
        }

        for(String idCodeCountStr : idCodeCountArray){
            String[] idCodeCount = idCodeCountStr.split("_");
            long orderTMId = Long.parseLong(idCodeCount[0]);
            int count = Integer.parseInt(idCodeCount[2]);

            if(null != mergeStudentBookOrderTMList && 0 < mergeStudentBookOrderTMList.size()){
                for(StudentBookOrderTM studentBookOrderTM : mergeStudentBookOrderTMList){
                    if(orderTMId == studentBookOrderTM.getId()){
                        mergeStudentBookOrderTMList.remove(studentBookOrderTM);
                        if(count != studentBookOrderTM.getCount()){
                            studentBookOrderTM.setCount(count);
                            editStudentBookOrderTMList.add(studentBookOrderTM);
                        }
                        confirmOrderTotalPrice = new BigDecimal(confirmOrderTotalPrice).add(new BigDecimal(count).multiply(new BigDecimal(studentBookOrderTM.getPrice()))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        break;
                    }
                }
            }
        }

        if(acc < confirmOrderTotalPrice + confirmOrderPrice){
            jsonObject.put("state", -1);
            jsonObject.put("acc", acc);
            jsonObject.put("confirmOrderTotalPrice", confirmOrderTotalPrice);
        }else{
            long issueChannelId = 0;
            //修改订单状态
            if(null != editStudentBookOrderList && 0 < editStudentBookOrderList.size()){
                for(StudentBookOrder editStudentBookOrder : editStudentBookOrderList){
                    issueChannelId = editStudentBookOrder.getIssueChannelId();
                    this.confirmStudentBookOrder(editStudentBookOrder.getId(), student.getSpotCode(), loginName);
                }
            }

            //修改订单明细教材数量
            if(null != editStudentBookOrderTMList && 0 < editStudentBookOrderTMList.size()){
                batchStudentBookOrderTMDAO.batchUpdate(editStudentBookOrderTMList, 100);
            }

            //如果有要合并的订单明细就要生成合并订单
            if(null != mergeStudentBookOrderTMList && 0 < mergeStudentBookOrderTMList.size()) {
                //获取当前学期
                Semester semester = findNowSemesterService.getNowSemester();
                //得到当前学期最大的订单号
                int num = 0;
                StudentBookOrder maxCodeStudentBookOrder = findStudentBookOrderForMaxCodeDAO.getStudentBookOrderForMaxCode(semester.getId());
                if (null != maxCodeStudentBookOrder) {
                    String maxOrderCode = maxCodeStudentBookOrder.getOrderCode();
                    num = Integer.parseInt(maxOrderCode.substring(maxOrderCode.length() - 6, maxOrderCode.length()));
                }
                //生成学生订单号
                String orderCode = OrderCodeTools.createStudentOrderCodeAuto(semester.getYear(), semester.getQuarter(), num + 1);
                //添加订单信息
                StudentBookOrder newStudentBookOrder = new StudentBookOrder();
                newStudentBookOrder.setSemesterId(semester.getId());
                newStudentBookOrder.setIssueChannelId(issueChannelId);
                newStudentBookOrder.setOrderCode(orderCode);
                newStudentBookOrder.setStudentCode(studentCode);
                newStudentBookOrder.setState(StudentBookOrder.STATE_UNCONFIRMED);
                newStudentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
                newStudentBookOrder.setIsSpotOrder(StudentBookOrder.ISSPOTORDER_NOT);
                newStudentBookOrder.setStudentSign(StudentBookOrder.STUDENTSIGN_NOT);
                newStudentBookOrder.setCreator(loginName);
                newStudentBookOrder.setOperator(loginName);
                findStudentBookOrderForMaxCodeDAO.save(newStudentBookOrder);

                //修改合并订单明细的订单号
                for(StudentBookOrderTM studentBookOrderTM : mergeStudentBookOrderTMList){
                    studentBookOrderTM.setOrderCode(newStudentBookOrder.getOrderCode());
                }
                batchStudentBookOrderTMDAO.batchUpdate(mergeStudentBookOrderTMList, 100);
            }
            jsonObject.put("state", 0);
        }

        return jsonObject;
    }
}
