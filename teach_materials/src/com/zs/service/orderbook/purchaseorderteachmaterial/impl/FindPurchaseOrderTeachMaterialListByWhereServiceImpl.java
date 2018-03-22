package com.zs.service.orderbook.purchaseorderteachmaterial.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.orderbook.purchaseorderteachmaterial.FindPurchaseOrderTeachMaterialListByWhereService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/12.
 */
@Service("findPurchaseOrderTeachMaterialListByWhereService")
public class FindPurchaseOrderTeachMaterialListByWhereServiceImpl
        extends EntityServiceImpl implements FindPurchaseOrderTeachMaterialListByWhereService {

    @Resource
    private FindListByWhereDAO findPurchaseOrderTeachMaterialListByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONArray getPurchaseOrderTeachMaterialListByWhere(String semesterId, String channelId, String tmTypeId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("semesterId", semesterId+"");
        params.put("channelId", channelId+"");
        params.put("tmTypeId", tmTypeId+"");
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("po.code", true);
        sortMap.put("tmt.code", true);
        List<Object[]> resultList = findPurchaseOrderTeachMaterialListByWhereDAO.findListByWhere(params, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", objs[0]);
                jsonObject.put("channelName", objs[1]);
                jsonObject.put("tmTypeName", objs[2]);
                jsonObject.put("tmCount", objs[3]);
                jsonObject.put("price", objs[4]);
                jsonObject.put("creator", objs[5]);
                jsonObject.put("createTime", objs[6]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
