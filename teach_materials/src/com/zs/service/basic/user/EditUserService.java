package com.zs.service.basic.user;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.User;

/**
 * 编辑用户信息
 * Created by Allen on 2015/4/27.
 */
public interface EditUserService extends EntityService<User> {

    /**
     * 编辑用户信息
     * @param user
     * @param loginName
     * @throws Exception
     */
    public void editUser(User user, String loginName)throws Exception;
}
