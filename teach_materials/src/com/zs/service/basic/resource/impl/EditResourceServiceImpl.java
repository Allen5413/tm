package com.zs.service.basic.resource.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.resource.FindResourceByNameAndMenuIdDAO;
import com.zs.dao.basic.resource.ResourceDAO;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.EditResourceService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;


/**
 * 实现修改资源接口
 * Created by Allen on 2015/4/27.
 */
@Service("editResourceService")
public class EditResourceServiceImpl extends EntityServiceImpl<Resource, ResourceDAO> implements EditResourceService {

    @javax.annotation.Resource
    private FindResourceByNameAndMenuIdDAO findResourceByNameAndMenuIdDAO;

    @Override
    public void editResource(Resource resource, String loginName) throws Exception {
        if(null != resource) {
            //是否存在
            Resource isExistsResource = super.get(resource.getId());
            if(null == isExistsResource){
                throw new BusinessException("资源不存在！");
            }
            //查询相同菜单下，资源名称是否已经存在
            Resource validResource = findResourceByNameAndMenuIdDAO.getResourceByNameAndMenuID(resource.getName(), resource.getMenuId());
            if(null != validResource && validResource.getName().equals(resource.getName()) && validResource.getId() != resource.getId()){
                throw new BusinessException("资源名称已经存在！");
            }
            resource.setOperator(loginName);
            resource.setCreator(isExistsResource.getCreator());
            resource.setCreateTime(isExistsResource.getCreateTime());
            super.update(resource);
        }
    }
}
