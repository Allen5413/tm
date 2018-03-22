package com.zs.service.placeorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.CourseEnum;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sync.Spec;
import com.zs.domain.sync.Spot;
import com.zs.service.placeorder.FindPlaceOrderTMForSpotPrintService;
import com.zs.service.placeorder.PlaceOrderService;
import com.zs.service.placeorder.bean.PlaceOrderStatus;
import com.zs.service.sync.spec.FindSpecByCodeService;
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
 * Created by Allen on 2015/8/1.
 */
@Service("findPlaceOrderTMForSpotPrintService")
public class FindPlaceOrderTMForSpotPrintServiceImpl extends EntityServiceImpl
        implements FindPlaceOrderTMForSpotPrintService {

    @Resource
    private FindListByWhereDAO findPlaceOrderTMForSpotPrintDAO;
    @Resource
    private PlaceOrderService placeOrderService;
    @Resource
    private FindSpotByCodeDAO findSpotByCodeDAO;

    @Override
    @Transactional
    public JSONObject findPlaceOrderTMForSpotPrint(HttpServletRequest request, Map<String, String> paramsMap, Map<String, Boolean> sortMap)throws Exception {
        List<Object[]> resultList = findPlaceOrderTMForSpotPrintDAO.findListByWhere(paramsMap, sortMap);
        JSONObject result = new JSONObject();
        if(null != resultList && 0 < resultList.size()){
            String spotCode = paramsMap.get("spotCode");
            //获取学习中心
            Spot spot = findSpotByCodeDAO.getSpotByCode(spotCode);
            if(null == spot){
                throw new BusinessException("没有找到学习中心");
            }
            String beforeOrderCode = "";
            int i = 0;
            int orderTmCount = 0;
            float orderTotalPrice = 0;
            Timestamp operateTime = DateTools.getLongNowTime();
            for(Object[] objs : resultList){
                //订单号
                String orderCode = objs[4].toString();
                //专业编号
                String specCode = objs[0].toString();
                float price = Float.parseFloat(objs[8].toString());
                int count = Integer.parseInt(objs[9].toString());
                String state = objs[10].toString();

                JSONObject jsonObject = new JSONObject();
                JSONArray tmJSONArray = new JSONArray();

                //如果当前订单号与之前不一样，填订单信息
                if(i == 0 || !beforeOrderCode.equals(orderCode)){
                    //订单填值
                    jsonObject.put("spotCode", spot.getCode());
                    jsonObject.put("spotName", spot.getName());
                    jsonObject.put("specName", SpecEnum.getDescn(specCode));
                    jsonObject.put("levelName", LevelEnum.getDescn(objs[1].toString()));
                    jsonObject.put("enYear", objs[2]);
                    jsonObject.put("enQuarter", objs[3]);
                    jsonObject.put("orderCode", orderCode);
                    jsonObject.put("address", objs[12]);
                    jsonObject.put("adminName", objs[13]);
                    if(Integer.parseInt(state) < Integer.parseInt(TeachMaterialPlaceOrder.STATE_SORTING) && null != request) {
                        //修改订单状态为分拣中
                        placeOrderService.editSpotOrderForState(orderCode, null, PlaceOrderStatus.DISPATCH.getCode(), operateTime, UserTools.getLoginUserForName(request));
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
                tmJSONObject.put("courseCode", objs[5]);
                tmJSONObject.put("courseName", CourseEnum.getDescn(objs[5].toString()));
                tmJSONObject.put("tmName", objs[6]);
                tmJSONObject.put("author", objs[7]);
                tmJSONObject.put("price", price);
                tmJSONObject.put("count", count);
                tmJSONObject.put("totalPrice", objs[11]);
                tmJSONArray.add(tmJSONObject);
                jsonObject.put("orderTM", tmJSONArray);

                result.put(orderCode, jsonObject);

                beforeOrderCode = orderCode;

                //计算该订单的教材数量
                orderTmCount += count;
                //计算该订单的教材总价
                orderTotalPrice = new BigDecimal(orderTotalPrice).add(new BigDecimal(objs[11].toString())).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
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
