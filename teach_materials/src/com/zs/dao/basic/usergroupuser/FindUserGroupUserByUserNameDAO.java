package com.zs.dao.basic.usergroupuser;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.UserGroupUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/18.
 */
public interface FindUserGroupUserByUserNameDAO extends EntityJpaDao<UserGroupUser, Long> {
    @Query("from UserGroupUser where userName = ?1")
    public List<UserGroupUser> find(String userName);
}
