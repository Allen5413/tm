package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.statis.FindSendTeachMaterialService;
import com.zs.tools.StringTools;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  统计一个学期订单发出情况
 * Created by Allen on 2015/12/1.
 */
@Service("findSendTeachMaterialService")
public class FindSendTeachMaterialServiceImpl extends EntityServiceImpl implements FindSendTeachMaterialService {

    @Resource
    private FindListByWhereDAO findSendTeachMaterialDAO;

    @Override
    @Transactional
    public List<JSONObject> findListByWhere(Map<String, String > paramsMap)throws Exception{
        List<JSONObject> returnList = new ArrayList<JSONObject>();
        List<Object[]> resultList = findSendTeachMaterialDAO.findListByWhere(paramsMap, null);

        if(null != resultList && 0 < resultList.size()){
            for(Object[] objs : resultList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", objs[0]);
                jsonObject.put("pName", objs[1]);
                jsonObject.put("author", objs[2]);
                jsonObject.put("price", objs[3]);
                jsonObject.put("count", objs[4]);
                returnList.add(jsonObject);
            }
        }
        return returnList;
    }
}
