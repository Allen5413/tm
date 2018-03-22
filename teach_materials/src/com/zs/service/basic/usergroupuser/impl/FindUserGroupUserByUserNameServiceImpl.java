package com.zs.service.basic.usergroupuser.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.usergroupuser.FindUserGroupUserByUserNameDAO;
import com.zs.domain.basic.UserGroupUser;
import com.zs.service.basic.usergroupuser.FindUserGroupUserByUserNameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2016/11/2.
 */
@Service("findUserGroupUserByUserNameService")
public class FindUserGroupUserByUserNameServiceImpl extends EntityServiceImpl<UserGroupUser, FindUserGroupUserByUserNameDAO>
    implements FindUserGroupUserByUserNameService {

    @Resource
    private FindUserGroupUserByUserNameDAO findUserGroupUserByUserNameDAO;

    @Override
    public List<UserGroupUser> find(String userName) throws Exception {
        return findUserGroupUserByUserNameDAO.find(userName);
    }
}
