package com.zs.service.sale.onceordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.CourseEnum;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.sale.onceordertm.FindOnceOrderTMForStudentPrintDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderTMForStudentPrintDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.EditOnceOrderForStateService;
import com.zs.service.sale.onceordertm.FindOnceOrderTMForStudentPrintService;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForStateService;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderTMForStudentPrintService;
import com.zs.tools.DateTools;
import com.zs.tools.UserTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Allen on 2016/10/24.
 */
@Service("findOnceOrderTMForStudentPrintService")
public class FindOnceOrderTMForStudentPrintServiceImpl extends EntityServiceImpl
        implements FindOnceOrderTMForStudentPrintService {

    @Resource
    private FindOnceOrderTMForStudentPrintDAO findOnceOrderTMForStudentPrintDAO;
    @Resource
    private EditOnceOrderForStateService editOnceOrderForStateService;

    @Override
    @Transactional
    public JSONObject print(long semesterId, int state, int pageNum, int countNum, String operateTime, HttpServletRequest request)throws Exception {
        List<Object[]> resultList = null;
        if(state == StudentBookOnceOrder.STATE_DOING) {
            resultList = findOnceOrderTMForStudentPrintDAO.find(semesterId, state, pageNum * countNum, countNum);
        }else{
            resultList = findOnceOrderTMForStudentPrintDAO.findPrint(semesterId, state, operateTime);
        }
        JSONObject result = new JSONObject();
        if(null != resultList && 0 < resultList.size()){
            String beforeOrderCode = "";
            int i = 0;
            int orderTmCount = 0;
            float orderTotalPrice = 0;
            Timestamp nowOperateTime = DateTools.getLongNowTime();
            for(Object[] objs : resultList){
                //订单号
                String orderCode = objs[4].toString();
                float price = Float.parseFloat(objs[9].toString());
                int count = Integer.parseInt(objs[10].toString());
                String courseCode = objs[6].toString();
                String courseName = CourseEnum.getDescn(courseCode);

                JSONObject jsonObject = new JSONObject();
                JSONArray tmJSONArray = new JSONArray();

                //如果当前订单号与之前不一样，填订单信息
                if(i == 0 || !beforeOrderCode.equals(orderCode)){
                    //订单填值
                    jsonObject.put("specName", SpecEnum.getDescn(objs[0].toString()));
                    jsonObject.put("levelName", LevelEnum.getDescn(objs[1].toString()));
                    jsonObject.put("code", objs[2]);
                    jsonObject.put("name", objs[3]);
                    jsonObject.put("orderCode", orderCode);
                    jsonObject.put("state", state);
                    jsonObject.put("isSendStudent", null == objs[11] ? "0" : objs[11].toString());
                    jsonObject.put("sendAddress", null == objs[12] ? "" : objs[12].toString());
                    jsonObject.put("sendPhone", null == objs[13] ? "" : objs[13].toString());
                    jsonObject.put("sendZipCode", null == objs[14] ? "" : objs[14].toString());
                    jsonObject.put("spotCode", null == objs[15] ? "" : objs[15].toString());
                    jsonObject.put("spotName", null == objs[16] ? "" : objs[16].toString());
                    if(state == StudentBookOnceOrder.STATE_DOING && null != request) {
                        //修改订单状态为分拣中
                        editOnceOrderForStateService.editor(orderCode, null, StudentBookOnceOrder.STATE_SORTING, nowOperateTime, UserTools.getLoginUserForName(request));
                    }
                    if(0 < i){
                        //把计算后的订单教材总数量和订单总价 设置到之前的订单中；并清0，重新计算后面的订单
                        JSONObject jsonObject1 = (JSONObject) result.get(beforeOrderCode);
                        jsonObject1.put("orderTmCount", orderTmCount);
                        jsonObject1.put("orderTotalPrice", orderTotalPrice);
                        orderTmCount = 0;
                        orderTotalPrice = 0;
                    }
                }else{
                    jsonObject = (JSONObject) result.get(orderCode);
                    tmJSONArray = (JSONArray) jsonObject.get("orderTM");
                }

                //订单明细填值
                JSONObject tmJSONObject = new JSONObject();
                tmJSONObject.put("courseCode", courseCode);
                tmJSONObject.put("courseName", courseName);
                tmJSONObject.put("tmName", objs[7]);
                tmJSONObject.put("author", objs[8]);
                tmJSONObject.put("price", price);
                tmJSONObject.put("count", count);
                tmJSONObject.put("totalPrice", new BigDecimal(price).multiply(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                tmJSONArray.add(tmJSONObject);
                jsonObject.put("orderTM", tmJSONArray);

                result.put(orderCode, jsonObject);

                beforeOrderCode = orderCode;

                //计算该订单的教材数量
                orderTmCount += count;
                //计算该订单的教材总价
                orderTotalPrice = new BigDecimal(orderTotalPrice).add(new BigDecimal(new BigDecimal(price).multiply(new BigDecimal(count)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue())).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                //如果执行到最后一条数据
                if(i == resultList.size() - 1){
                    //把计算后的订单教材总数量和订单总价 设置到之前的订单中；并清0，重新计算后面的订单
                    JSONObject jsonObject1 = (JSONObject) result.get(orderCode);
                    jsonObject1.put("orderTmCount", orderTmCount);
                    jsonObject1.put("orderTotalPrice", orderTotalPrice);
                }
                i++;
            }
        }
        return result;
    }
}
