package com.zs.service.basic.resource.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.resource.FindResourceForMenuByUserGroupIdDAO;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourceForMenuByUserGroupIdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2016/9/26.
 */
@Service("findResourceForMenuByUserGroupIdService")
public class FindResourceForMenuByUserGroupIdServiceImpl extends EntityServiceImpl<Resource, FindResourceForMenuByUserGroupIdDAO> implements FindResourceForMenuByUserGroupIdService{

    @javax.annotation.Resource
    private FindResourceForMenuByUserGroupIdDAO findResourceForMenuByUserGroupIdDAO;

    @Override
    public JSONArray find(long userGroupId) {
        JSONArray jsonArray = new JSONArray();
        List<Object[]> list = findResourceForMenuByUserGroupIdDAO.find(userGroupId);
        for(Object[] objs : list){
            String ztreeId = objs[0].toString();
            String name = objs[1].toString();
            long pId = Long.parseLong(objs[2].toString());
            long rId = Long.parseLong(objs[3].toString());
            boolean isCheck = null == objs[4] ? false : true;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ztreeId", ztreeId);
            jsonObject.put("name", name);
            jsonObject.put("pId", pId);
            jsonObject.put("rId", rId);
            jsonObject.put("isCheck", isCheck);

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
