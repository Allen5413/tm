package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.sale.onceorder.CountSendOnceOrderByTMIdService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/4.
 */
@Service("countSendOnceOrderByTMIdService")
public class CountSendOnceOrderByTMIdServiceImpl extends EntityServiceImpl
        implements CountSendOnceOrderByTMIdService {

    @Resource
    private FindListByWhereDAO countSendOnceOrderByTMIdDAO;

    @Override
    @Transactional
    public JSONArray find(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object[]> resultList = countSendOnceOrderByTMIdDAO.findListByWhere(paramsMap, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderCode", objs[0]);
                jsonObject.put("spotCode", objs[1]);
                jsonObject.put("spotName", objs[2]);
                jsonObject.put("code", objs[3]);
                jsonObject.put("name", objs[4]);
                jsonObject.put("price", objs[5]);
                jsonObject.put("count", objs[6]);
                jsonObject.put("sbotmId", objs[7]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
