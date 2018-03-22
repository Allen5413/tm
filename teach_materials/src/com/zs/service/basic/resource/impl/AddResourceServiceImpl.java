package com.zs.service.basic.resource.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.resource.FindResourceByNameAndMenuIdDAO;
import com.zs.dao.basic.resource.ResourceDAO;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.AddResourceService;
import org.springframework.stereotype.Service;

/**
 * 实现新增资源接口
 * Created by Allen on 2015/4/27.
 */
@Service("addResourceService")
public class AddResourceServiceImpl extends EntityServiceImpl<Resource, ResourceDAO> implements AddResourceService {

    @javax.annotation.Resource
    private FindResourceByNameAndMenuIdDAO findResourceByNameAndMenuIdDAO;

    @Override
    public void addResource(Resource resource, String loginName) throws Exception {
        if(null != resource){
            //查询相同菜单下，资源名称是否已经存在
            Resource validResource = findResourceByNameAndMenuIdDAO.getResourceByNameAndMenuID(resource.getName(), resource.getMenuId());
            if(null != validResource && validResource.getName().equals(resource.getName())){
                throw new BusinessException("资源名称已经存在！");
            }
            resource.setCreator(loginName);
            resource.setOperator(loginName);
            super.save(resource);
        }
    }
}
