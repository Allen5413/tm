package com.zs.dao.basic.user;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.User;
import org.springframework.data.jpa.repository.Query;

/**
 * 通过登录名查询用户信息
 * Created by Allen on 2015/4/27.
 */
public interface FindUserByLoginNameDAO extends EntityJpaDao<User,Long> {

    @Query("from User where loginName = ?1")
    public User getUserByLoginName(String loginName);
}
