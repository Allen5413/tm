package com.zs.service.placeorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.placeorder.CountSendPlaceOrderByTMIdService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/16.
 */
@Service("countSendPlaceOrderByTMIdService")
public class CountSendPlaceOrderByTMIdServiceImpl extends EntityServiceImpl
        implements CountSendPlaceOrderByTMIdService {

    @Resource
    private FindListByWhereDAO countSendPlaceOrderByTMIdDAO;

    @Override
    @Transactional
    public JSONArray countSendPlaceOrderByTMId(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object[]> resultList = countSendPlaceOrderByTMIdDAO.findListByWhere(paramsMap, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderCode", objs[0]);
                jsonObject.put("spotCode", objs[1]);
                jsonObject.put("spotName", objs[2]);
                jsonObject.put("price", objs[3]);
                jsonObject.put("count", objs[4]);
                jsonObject.put("potmId", objs[5]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
