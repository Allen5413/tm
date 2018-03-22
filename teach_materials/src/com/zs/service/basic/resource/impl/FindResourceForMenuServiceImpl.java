package com.zs.service.basic.resource.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.resource.FindResourceForMenuDAO;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourceForMenuService;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by Allen on 2016/9/25 0025.
 */
@Service("findResourceForMenuService")
public class FindResourceForMenuServiceImpl extends EntityServiceImpl<Resource, FindResourceForMenuDAO> implements FindResourceForMenuService{

    @javax.annotation.Resource
    private FindResourceForMenuDAO findResourceForMenuDAO;

    @Override
    public JSONArray find() {
        JSONArray jsonArray = new JSONArray();
        List<Object[]> list = findResourceForMenuDAO.find();
        for(Object[] objs : list){
            String ztreeId = objs[0].toString();
            String name = objs[1].toString();
            long pId = Long.parseLong(objs[2].toString());
            long rId = Long.parseLong(objs[3].toString());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ztreeId", ztreeId);
            jsonObject.put("name", name);
            jsonObject.put("pId", pId);
            jsonObject.put("rId", rId);

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
