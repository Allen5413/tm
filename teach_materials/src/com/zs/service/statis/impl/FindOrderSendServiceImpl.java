package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.statis.FindOrderCountBySemesterIdDAO;
import com.zs.domain.basic.Semester;
import com.zs.service.statis.FindOrderSendService;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/23.
 */
@Service("findOrderSendService")
public class FindOrderSendServiceImpl extends EntityServiceImpl implements FindOrderSendService {

    @Resource
    private FindListByWhereDAO findPlaceOrderForSendDAO;
    @Resource
    private FindOrderCountBySemesterIdDAO findOrderCountBySemesterIdDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    @Transactional
    public JSONObject findListByWhere()throws Exception{
        //获取当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("semesterId", semester.getId()+"");
        JSONObject result = new JSONObject();
        //查询已发送的订单数据
        List<Object[]> resultList = findPlaceOrderForSendDAO.findListByWhere(paramsMap, null);

        //查询当前学期的学生已经确认的订单数量
        BigInteger studentOrderCount = findOrderCountBySemesterIdDAO.findConfirmStudentOrderCountBySemesterId(semester.getId());
        studentOrderCount = null == studentOrderCount ? new BigInteger("0") : studentOrderCount;
        //查询当前学期的已经确认的一次性订单数量
        BigInteger onceOrderCount = findOrderCountBySemesterIdDAO.findConfirmOnceCountBySemesterId(semester.getId());
        onceOrderCount = null == onceOrderCount ? new BigInteger("0") : onceOrderCount;
        //查询当前学期的预订单已经确认的订单数量
        BigInteger placeOrderCount = findOrderCountBySemesterIdDAO.findPlaceOrderCountBySemesterId(semester.getId());
        placeOrderCount = null == placeOrderCount ? new BigInteger("0") : placeOrderCount;

        //查询当前学期还未发出的学生订单大包数量
        BigInteger studentPackageOrderCount = findOrderCountBySemesterIdDAO.findNotSendStudentOrderPackageCountBySemesterId(semester.getId());
        studentPackageOrderCount = null == studentPackageOrderCount ? new BigInteger("0") : studentPackageOrderCount;
        //查询当前学期还未发出的一次性订单大包数量
        BigInteger oncePackageOrderCount = findOrderCountBySemesterIdDAO.findNotSendOnceOrderPackageCountBySemesterId(semester.getId());
        oncePackageOrderCount = null == oncePackageOrderCount ? new BigInteger("0") : oncePackageOrderCount;
        //查询当前学期还未发出的预订单大包数量
        BigInteger placePackageOrderCount = findOrderCountBySemesterIdDAO.findNotSendPlaceOrderPackageCountBySemesterId(semester.getId());
        placePackageOrderCount = null == placePackageOrderCount ? new BigInteger("0") : placePackageOrderCount;

        //查询当前学期还未发出的学生订单总金额
        Double notSendStudentOrderPrice = findOrderCountBySemesterIdDAO.findNotSendStudentOrderPriceBySemesterId(semester.getId());
        notSendStudentOrderPrice = null == notSendStudentOrderPrice ? 0 : notSendStudentOrderPrice;
        //查询当前学期还未发出的一次性订单总金额
        Double notSendOnceOrderPrice = findOrderCountBySemesterIdDAO.findNotSendOnceOrderPriceBySemesterId(semester.getId());
        notSendOnceOrderPrice = null == notSendOnceOrderPrice ? 0 : notSendOnceOrderPrice;
        //查询当前学期还未发出的预订单总金额
        Double notSendPlaceOrderPrice = findOrderCountBySemesterIdDAO.findNotSendPlaceOrderPriceBySemesterId(semester.getId());
        notSendPlaceOrderPrice = null == notSendPlaceOrderPrice ? 0 : notSendPlaceOrderPrice;

        //未发出订单总金额
        double unSendOrderPrice = notSendStudentOrderPrice + notSendPlaceOrderPrice + notSendOnceOrderPrice;
        //已发出的订单总金额
        double sendOrderPrice = 0;
        //订单总金额
        double orderPrice = 0;
        //未发出的订单数
        int unorSendCount = studentOrderCount.intValue() + placeOrderCount.intValue() + onceOrderCount.intValue();


        if(null != resultList && 0 < resultList.size()){
            JSONArray jsonArray = new JSONArray();
            //计算已发出的订单总金额
            for(Object[] objs : resultList){
                double price = Double.parseDouble(objs[3].toString());
                sendOrderPrice += price;
            }
            orderPrice = unSendOrderPrice + sendOrderPrice;

            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                String date = objs[0].toString();
                int orderCount = Integer.parseInt(objs[1].toString());
                int packageCount = Integer.parseInt(objs[2].toString());
                float price = Float.parseFloat(objs[3].toString());
                jsonObject.put("date", date);
                jsonObject.put("orderCount", orderCount);
                jsonObject.put("packageCount", packageCount);
                jsonObject.put("price", StringTools.getFinancePrice(price+""));
                jsonObject.put("percent", new BigDecimal(100 * price).divide(new BigDecimal(orderPrice), 2, BigDecimal.ROUND_HALF_UP)+"%");
                jsonArray.add(jsonObject);
                unorSendCount -= orderCount;
            }
            result.put("sendOrderList", jsonArray);
        }
        result.put("unSendOrderCount", unorSendCount);
        result.put("unSendOrderPackageCount", studentPackageOrderCount.intValue() + placePackageOrderCount.intValue() + oncePackageOrderCount.intValue());
        result.put("unSendOrderPrice", StringTools.getFinancePrice(new BigDecimal(unSendOrderPrice).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
        result.put("unSendOrderPercent", 0 == orderPrice ? 0+"%" : new BigDecimal(100 * unSendOrderPrice).divide(new BigDecimal(orderPrice), 2, BigDecimal.ROUND_HALF_UP)+"%");
        result.put("semesterStr", semester.getSemester2());
        return result;
    }
}
