package com.zs.dao.basic.user;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/25.
 */
public interface EditUserPasswordDAO extends EntityJpaDao<User, Long> {
    @Modifying
    @Query("update User set password = ?1 where loginName = ?2 and password = ?3")
    public void editUserPassword(String newPwd, String loginName, String oldPwd)throws Exception;
}
