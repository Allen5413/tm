package com.zs.service.basic.user;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.User;

/**
 * Created by Allen on 2015/5/25.
 */
public interface EditUserPasswordService extends EntityService<User> {
    public void editUserPassword(String newPwd, String loginName, String oldPwd)throws Exception;
}
