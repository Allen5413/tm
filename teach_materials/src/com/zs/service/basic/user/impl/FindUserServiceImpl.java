package com.zs.service.basic.user.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.user.UserDAO;
import com.zs.domain.basic.User;
import com.zs.service.basic.user.FindUserService;
import org.springframework.stereotype.Service;

/**
 * 实现查询用户信息接口
 * Created by Allen on 2015/4/27.
 */
@Service("findUserService")
public class FindUserServiceImpl extends EntityServiceImpl<User, UserDAO> implements FindUserService {
}
