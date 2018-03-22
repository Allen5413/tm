package com.zs.service.basic.usergroup.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.usergroup.FindUserGroupByUserNameDAO;
import com.zs.domain.basic.UserGroup;
import com.zs.service.basic.usergroup.FindUserGroupByUserNameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/15.
 */
@Service("findUserGroupByUserNameService")
public class FindUserGroupByUserNameServiceImpl extends EntityServiceImpl<UserGroup, FindUserGroupByUserNameDAO>
    implements FindUserGroupByUserNameService{

    @Resource
    private FindUserGroupByUserNameDAO findUserGroupByUserNameDAO;

    @Override
    public List<UserGroup> getUserGroupByUserName(String userName) {
        return findUserGroupByUserNameDAO.getUserGroupByUserName(userName);
    }
}
