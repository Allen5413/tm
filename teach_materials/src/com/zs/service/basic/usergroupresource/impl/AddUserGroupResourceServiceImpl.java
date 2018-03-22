package com.zs.service.basic.usergroupresource.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.usergroupresource.UserGroupResourceDAO;
import com.zs.domain.basic.UserGroupResource;
import com.zs.service.basic.usergroupresource.AddUserGroupResourceService;
import org.springframework.stereotype.Service;

/**
 * 实现新增用户组关联资源接口
 * Created by Allen on 2015/4/27.
 */
@Service("addUserGroupResourceService")
public class AddUserGroupResourceServiceImpl extends EntityServiceImpl<UserGroupResource, UserGroupResourceDAO> implements AddUserGroupResourceService {

    @Override
    public void addUserGroupResource(UserGroupResource userGroupResource, String loginName) throws Exception {
        if(null != userGroupResource){
            userGroupResource.setCreator(loginName);
            super.save(userGroupResource);
        }
    }
}
