package com.zs.service.placeorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.placeorder.FindPlaceOrderForSpotPrintService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 打印学习中心发书单
 * Created by Allen on 2015/8/1.
 */
@Service("findPlaceOrderForSpotPrintService")
public class FindPlaceOrderForSpotPrintServiceImpl extends EntityServiceImpl
        implements FindPlaceOrderForSpotPrintService {

    @Resource
    private FindListByWhereDAO findPlaceOrderForSpotPrintDAO;

    @Override
    @Transactional
    public JSONArray findPlaceOrderForSpotPrint(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object[]> resultList = findPlaceOrderForSpotPrintDAO.findListByWhere(paramsMap, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("deptName", objs[0]);
                jsonObject.put("spotCode", objs[1]);
                jsonObject.put("spotName", objs[2]);
                jsonObject.put("tmName", objs[3]);
                jsonObject.put("author", objs[4]);
                jsonObject.put("price", objs[5]);
                jsonObject.put("courseCode", objs[6]);
                jsonObject.put("count", objs[7]);
                jsonObject.put("totalPrice", objs[8]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
