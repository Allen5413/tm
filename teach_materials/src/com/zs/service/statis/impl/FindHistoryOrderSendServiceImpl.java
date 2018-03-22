package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.statis.FindHistoryOrderSendDAO;
import com.zs.service.statis.FindHistoryOrderSendService;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/9/25.
 */
@Service("findHistoryOrderSendService")
public class FindHistoryOrderSendServiceImpl extends EntityServiceImpl implements FindHistoryOrderSendService {

    @Resource
    private FindHistoryOrderSendDAO findHistoryOrderSendDAO;

    @Override
    public JSONArray findHistoryOrderSend(String isSpot) throws Exception {
        JSONArray jsonArray = new JSONArray();
        //不按学习中心统计
        if(StringUtils.isEmpty(isSpot)) {
            List<Object[]> resultList = findHistoryOrderSendDAO.findHistoryOrderSend();
            if (null != resultList && 0 < resultList.size()) {
                for (Object[] objs : resultList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("semesterId", objs[0]);
                    jsonObject.put("semester", objs[1] + "年" + objs[2]);
                    jsonObject.put("orderCount", null == objs[3] ? 0 : objs[3]);
                    jsonObject.put("packageCount", null == objs[4] ? 0 : objs[4]);
                    jsonObject.put("price", null == objs[5] ? 0 : StringTools.getFinancePrice(objs[5].toString()));
                    jsonArray.add(jsonObject);
                }
            }
        }else{
            //按照学习中心统计
            List<Object[]> resultList = findHistoryOrderSendDAO.findHistoryOrderSendForSpot();
            if (null != resultList && 0 < resultList.size()) {
                for (Object[] objs : resultList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("semester", objs[0] + "年" + objs[1]);
                    jsonObject.put("spotCode", objs[2]);
                    jsonObject.put("spotName", objs[3]);
                    jsonObject.put("orderCount", null == objs[4] ? 0 : objs[4]);
                    jsonObject.put("packageCount", null == objs[5] ? 0 : objs[5]);
                    jsonObject.put("price", null == objs[6] ? 0 : StringTools.getFinancePrice(objs[6].toString()));
                    jsonArray.add(jsonObject);
                }
            }
        }
        return jsonArray;
    }

    @Override
    public JSONArray findSpotOrderSendBySemesterId(long semesterId) throws Exception {
        JSONArray jsonArray = new JSONArray();
        List<Object[]> resultList = findHistoryOrderSendDAO.findSpotOrderSendBySemesterId(semesterId);
        if(null != resultList && 0 < resultList.size()){
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spotCode", objs[0]);
                jsonObject.put("spotName", objs[1]);
                jsonObject.put("orderCount", null == objs[2] ? 0 : objs[2]);
                jsonObject.put("packageCount", null == objs[3] ? 0 : objs[3]);
                jsonObject.put("price", null == objs[4] ? 0 : StringTools.getFinancePrice(objs[4].toString()));
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }
}
