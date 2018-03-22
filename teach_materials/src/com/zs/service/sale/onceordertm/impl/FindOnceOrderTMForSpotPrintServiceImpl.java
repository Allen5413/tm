package com.zs.service.sale.onceordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.CourseEnum;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.EditOnceOrderForStateService;
import com.zs.service.sale.onceordertm.FindOnceOrderTMForSpotPrintService;
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
import java.util.Map;

/**
 * 打印学生发书单
 * Created by Allen on 2015/7/19.
 */
@Service("findOnceOrderTMForSpotPrintService")
public class FindOnceOrderTMForSpotPrintServiceImpl extends EntityServiceImpl
        implements FindOnceOrderTMForSpotPrintService {

    @Resource
    private FindListByWhereDAO findOnceOrderTMForSpotPrintDAO;
    @Resource
    private EditOnceOrderForStateService editOnceOrderForStateService;

    @Override
    @Transactional
    public JSONObject find(HttpServletRequest request, Map<String, String> paramsMap, Map<String, Boolean> sortMap)throws Exception {
        List<Object[]> resultList = findOnceOrderTMForSpotPrintDAO.findListByWhere(paramsMap, sortMap);
        JSONObject result = new JSONObject();
        if(null != resultList && 0 < resultList.size()){
            String beforeOrderCode = "";
            int i = 0;
            int orderTmCount = 0;
            float orderTotalPrice = 0;
            Timestamp operateTime = DateTools.getLongNowTime();
            for(Object[] objs : resultList){
                //订单号
                String orderCode = objs[4].toString();
                int state = Integer.parseInt(objs[5].toString());
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
                    if(state == StudentBookOnceOrder.STATE_DOING && null != request) {
                        //修改订单状态为分拣中
                        editOnceOrderForStateService.editor(orderCode, null, StudentBookOnceOrder.STATE_SORTING, operateTime, UserTools.getLoginUserForName(request));
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
