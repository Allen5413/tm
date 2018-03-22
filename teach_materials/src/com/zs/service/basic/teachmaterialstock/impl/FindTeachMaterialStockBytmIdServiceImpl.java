package com.zs.service.basic.teachmaterialstock.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.basic.teachmaterialstock.FindTeachMaterialStockBytmIdService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/20.
 */
@Service("findTeachMaterialStockBytmIdService")
public class FindTeachMaterialStockBytmIdServiceImpl extends EntityServiceImpl implements FindTeachMaterialStockBytmIdService {

    @Resource
    private FindListByWhereDAO findTeachMaterialStockBytmIdDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONArray getTeachMaterialStockBytmId(Long tmId) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("tmId", tmId+"");
        List<Object[]> resultList = findTeachMaterialStockBytmIdDAO.findListByWhere(params, null);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", objs[0]);
                jsonObject.put("issueChannelId", objs[1]);
                jsonObject.put("name", objs[2]);
                jsonObject.put("stock", objs[3]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
