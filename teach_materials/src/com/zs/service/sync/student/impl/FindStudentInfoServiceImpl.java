package com.zs.service.sync.student.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpense.FindTotalPayBuyByStudentCodeDAO;
import com.zs.dao.kuaidi.push.FindKuaidiPushByNuDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByStudentCodeForStudentInfoDAO;
import com.zs.dao.sale.studentbookordertm.FindStudentBookOrderTMByStudentCodeDAO;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.kuaidi.KuaidiPush;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderPackage;
import com.zs.domain.sync.Spot;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.FindStudentInfoService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 2015/10/20.
 */
@Service("findStudentInfoService")
public class FindStudentInfoServiceImpl extends EntityServiceImpl<Student, StudentDAO>
    implements FindStudentInfoService{

    @Resource
    private FindTotalPayBuyByStudentCodeDAO findTotalPayBuyByStudentCodeDAO;
    @Resource
    private FindStudentBookOrderTMByStudentCodeDAO findStudentBookOrderTMByStudentCodeDAO;
    @Resource
    private FindSpotByCodeDAO findSpotByCodeDAO;
    @Resource
    private FindStudentBookOrderByStudentCodeForStudentInfoDAO findStudentBookOrderByStudentCodeForStudentInfoDAO;
    @Resource
    private FindKuaidiPushByNuDAO findKuaidiPushByNuDAO;

    @Override
    public JSONObject findStudentInfo(String code, String spotCode) throws Exception {
        JSONObject jsonObject = new JSONObject();
        //查询该学习中心是否开放给学生访问
        Spot spot = findSpotByCodeDAO.getSpotByCode(spotCode);
        if(null == spot){
            throw new BusinessException("非常抱歉，没有找到你所在的学习中心！");
        }
        if(spot.getIsStudentLogin() == Spot.IS_STUDENT_LOGIN_NOT){
            throw new BusinessException("非常抱歉，你所在的学习中心没有开放权限，请与学习中心联系！");
        }

        //查询财务信息
        this.findFinance(jsonObject, code);
        //查询未确认的订购教材明细
        this.findOrderTM(jsonObject, code, StudentBookOrder.STATE_UNCONFIRMED);
        //查询正在处理的订单信息
        this.findDisposeOrder(jsonObject, code);
        //查询历史订购教材明细
        this.findOrderTM(jsonObject, code, StudentBookOrder.STATE_SIGN);
        return jsonObject;
    }

    protected void findFinance(JSONObject jsonObject, String code){
        double pay = 0, buy = 0, acc = 0;
        Object[] objects = findTotalPayBuyByStudentCodeDAO.find(code);
        if(null != objects && 0 < objects.length){
            pay = Double.parseDouble(objects[0].toString());
            buy = Double.parseDouble(objects[1].toString());
            acc = new BigDecimal(pay).subtract(new BigDecimal(buy)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        jsonObject.put("pay", pay);
        jsonObject.put("buy", buy);
        jsonObject.put("acc", acc < 0 ? 0 : acc);
    }

    protected void findDisposeOrder(JSONObject jsonObject, String code){
        int disposeTotalCount = 0;
        double disposeTotalPrice = 0;
        JSONArray jsonArray = new JSONArray();
        List<Object[]> list = findStudentBookOrderByStudentCodeForStudentInfoDAO.find(code);
        if(null != list && 0 < list.size()){
            for (Object[] objs : list) {
                if (null != objs[0] && null != objs[1]) {
                    int count = Integer.parseInt(objs[3].toString());
                    double price = Double.parseDouble(objs[4].toString());
                    String logisticCode = null == objs[5] ? "" : objs[5].toString();
                    //得到快递信息
                    JSONArray kuaidiJSON = null;
                    if (null != objs[6]) {
                        kuaidiJSON = JSONArray.fromObject(objs[6]);
                    }
                    //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                    StudentBookOrder studentBookOrder = new StudentBookOrder();
                    studentBookOrder.setCreateTime((Date) objs[7]);
                    JsonConfig jsonConfig = new JsonConfig();
                    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                    JSONObject orderJSON = JSONObject.fromObject(studentBookOrder, jsonConfig);
                    orderJSON.put("id", objs[0]);
                    orderJSON.put("orderCode", objs[1]);
                    orderJSON.put("state", objs[2]);
                    orderJSON.put("count", count);
                    orderJSON.put("price", price);
                    orderJSON.put("logisticCode", logisticCode);
                    if (null != kuaidiJSON && 0 < kuaidiJSON.size()) {
                        orderJSON.put("kuaidiJSON", kuaidiJSON.get(0));
                    } else {
                        if (!StringUtils.isEmpty(logisticCode)) {
                            String[] nus = logisticCode.split(",");
                            if (null != nus && 0 < nus.length) {
                                KuaidiPush kuaidiPush = findKuaidiPushByNuDAO.find(nus[0]);
                                if (null != kuaidiPush) {
                                    kuaidiJSON = JSONArray.fromObject(kuaidiPush.getData());
                                    if (null != kuaidiJSON && 0 < kuaidiJSON.size()) {
                                        orderJSON.put("kuaidiJSON", kuaidiJSON.get(0));
                                    }
                                }
                            }
                        }
                    }
                    jsonArray.add(orderJSON);
                    disposeTotalPrice = new BigDecimal(disposeTotalPrice).add(new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    disposeTotalCount += count;
                }
            }
        }
        jsonObject.put("disposeTotalCount", disposeTotalCount);
        jsonObject.put("disposeTotalPrice", disposeTotalPrice);
        jsonObject.put("disposeOrder", jsonArray);
    }

    protected void findOrderTM(JSONObject jsonObject, String code, int state){
        int orderTotalCount = 0;
        double orderTotalPrice = 0;
        JSONArray historyOrderTMArray = null;
        List<Object[]> list = findStudentBookOrderTMByStudentCodeDAO.find(state, code);
        if(null != list && 0 < list.size()){
            historyOrderTMArray = new JSONArray();
            for(Object[] objs : list){
                double price = Double.parseDouble(objs[5].toString());
                int count = Integer.parseInt(objs[6].toString());
                double totalPrice = new BigDecimal(price).multiply(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                JSONObject orderTMJSON = new JSONObject();
                orderTMJSON.put("orderTMId", Long.parseLong(objs[0].toString()));
                orderTMJSON.put("orderCode", objs[1].toString());
                orderTMJSON.put("courseCode", objs[2].toString());
                orderTMJSON.put("courseName", objs[3].toString());
                orderTMJSON.put("tmName", objs[4].toString());
                orderTMJSON.put("price", price);
                orderTMJSON.put("count", count);
                orderTMJSON.put("totalPrice", totalPrice);
                historyOrderTMArray.add(orderTMJSON);
                orderTotalPrice = new BigDecimal(orderTotalPrice).add(new BigDecimal(totalPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                orderTotalCount += count;
            }
        }
        if(state == StudentBookOrder.STATE_SIGN) {
            jsonObject.put("historyOrderTM", historyOrderTMArray);
            jsonObject.put("historyTotalCount", orderTotalCount);
            jsonObject.put("historyTotalPrice", orderTotalPrice);
        }
        if(state == StudentBookOrder.STATE_UNCONFIRMED) {
            jsonObject.put("unconfirmedOrderTM", historyOrderTMArray);
            jsonObject.put("unconfirmedTotalCount", orderTotalCount);
            jsonObject.put("unconfirmedTotalPrice", orderTotalPrice);
        }
    }
}
