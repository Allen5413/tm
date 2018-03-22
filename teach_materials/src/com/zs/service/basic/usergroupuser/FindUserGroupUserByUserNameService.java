package com.zs.service.basic.usergroupuser;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.UserGroupUser;

import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
public interface FindUserGroupUserByUserNameService extends EntityService<UserGroupUser> {

    public List<UserGroupUser> find(String userName)throws Exception;
}
