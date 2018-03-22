package com.zs.service.basic.user.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.user.FindUserByLoginNameDAO;
import com.zs.dao.basic.user.UserDAO;
import com.zs.domain.basic.User;
import com.zs.service.basic.user.AddUserService;
import com.zs.tools.MD5Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 实现新增用户接口
 * Created by Allen on 2015/4/27.
 */
@Service("addUserService")
public class AddUserServiceImpl extends EntityServiceImpl<User, UserDAO> implements AddUserService {

    @Resource
    private FindUserByLoginNameDAO findUserByLoginNameDAO;

    @Override
    public void addUser(User user, String loginName) throws Exception {
        if(null != user){
            //验证登录名是否已经存在
            User validUser = findUserByLoginNameDAO.getUserByLoginName(user.getLoginName());
            if(null != validUser && validUser.getLoginName().equals(user.getLoginName())){
                throw new BusinessException("登录名已经存在");
            }
            user.setPassword(MD5Tools.MD5("123456"));
            user.setCreator(loginName);
            user.setOperator(loginName);
            super.save(user);
        }
    }
}
