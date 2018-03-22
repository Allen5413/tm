package com.zs.service.basic.usergroup.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.usergroup.FindUserGroupByNameDAO;
import com.zs.dao.basic.usergroup.UserGroupDAO;
import com.zs.dao.basic.usergroupresource.UserGroupResourceDAO;
import com.zs.domain.basic.UserGroup;
import com.zs.domain.basic.UserGroupResource;
import com.zs.service.basic.usergroup.AddUserGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 实现新增用户组接口
 * Created by Allen on 2015/4/27.
 */
@Service("addUserGroupService")
public class AddUserGroupServiceImpl extends EntityServiceImpl<UserGroup, UserGroupDAO> implements AddUserGroupService {

    @Resource
    private FindUserGroupByNameDAO findUserGroupByNameDAO;
    @Resource
    private UserGroupResourceDAO userGroupResourceDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void addUserGroup(UserGroup userGroup, String resourceIds, String loginName) throws Exception {
        if(null != userGroup){
            //查询用户组名称是否已经存在
            UserGroup validUserGroup = findUserGroupByNameDAO.getUserGroupByName(userGroup.getName());
            if(null != validUserGroup && validUserGroup.getName().equals(userGroup.getName())){
                throw new BusinessException("用户组名称已经存在！");
            }
            //添加用户组
            userGroup.setCreator(loginName);
            userGroup.setOperator(loginName);
            super.save(userGroup);
            //新增关联资源
            if(!StringUtils.isEmpty(resourceIds)){
                String[] resourceIdArray = resourceIds.split(",");
                if(null != resourceIdArray && 0 < resourceIdArray.length){
                    for(String resourceId : resourceIdArray){
                        UserGroupResource userGroupResource = new UserGroupResource();
                        userGroupResource.setUserGroupId(userGroup.getId());
                        userGroupResource.setResourceId(Long.parseLong(resourceId));
                        userGroupResource.setCreator(loginName);
                        userGroupResourceDAO.save(userGroupResource);
                    }
                }
            }
        }
    }
}
