package com.zs.service.basic.user.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.user.FindUserByLoginNameDAO;
import com.zs.dao.basic.user.UserDAO;
import com.zs.domain.basic.User;
import com.zs.service.basic.user.EditUserService;
import com.zs.tools.DateTools;
import com.zs.tools.MD5Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现修改用户接口
 * Created by Allen on 2015/4/27.
 */
@Service("editUserService")
public class EditUserServiceImpl extends EntityServiceImpl<User, UserDAO> implements EditUserService {

    @Resource
    private FindUserByLoginNameDAO findUserByLoginNameDAO;

    @Override
    public void editUser(User user, String loginName) throws Exception {
        if(null != user) {
            //是否存在
            User isExistsUser = super.get(user.getId());
            if(null == isExistsUser){
                throw new BusinessException("用户信息不存在！");
            }
            //验证登录名是否已经存在
            User validUser = findUserByLoginNameDAO.getUserByLoginName(user.getLoginName());
            if(null != validUser && validUser.getLoginName().equals(user.getLoginName()) && validUser.getId() != user.getId()){
                throw new BusinessException("登录名已经存在");
            }
            user.setPassword(isExistsUser.getPassword());
            user.setCreator(isExistsUser.getCreator());
            user.setCreateTime(isExistsUser.getCreateTime());
            user.setOperator(loginName);
            super.update(user);
        }
    }
}
