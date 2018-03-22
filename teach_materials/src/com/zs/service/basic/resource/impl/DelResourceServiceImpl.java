package com.zs.service.basic.resource.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.resource.ResourceDAO;
import com.zs.dao.basic.usergroupresource.FindUserGroupResourceByResourceIdDAO;
import com.zs.domain.basic.Resource;
import com.zs.domain.basic.UserGroupResource;
import com.zs.service.basic.resource.DelResourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 实现删除资源接口
 * Created by Allen on 2015/4/27.
 */
@Service("delResourceService")
public class DelResourceServiceImpl extends EntityServiceImpl<Resource, ResourceDAO> implements DelResourceService {

    @javax.annotation.Resource
    private FindUserGroupResourceByResourceIdDAO findUserGroupResourceByResourceIdDAO;

    @Override
    public void delResource(Long id) throws Exception {
        Resource resource = super.get(id);
        if(null != resource){
            //判断资源下面是否关联得有用户组信息，如果有就不能删除
            List<UserGroupResource> userGroupResourceSet = findUserGroupResourceByResourceIdDAO.getUserGroupResourceByResourceId(resource.getId());
            if(null != userGroupResourceSet && 0 < userGroupResourceSet.size()){
                throw new BusinessException("该资源关联得有用户组，不能被删除！");
            }
            super.remove(resource);
        }
    }
}
