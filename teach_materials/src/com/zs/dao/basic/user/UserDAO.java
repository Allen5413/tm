package com.zs.dao.basic.user;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.User;

/**
 * 用于不需要查询的service用
 * Created by Allen on 2015/4/27.
 */
public interface UserDAO extends EntityJpaDao<User,Long> {
}
