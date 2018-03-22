package com.zs.service.basic.user.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.user.EditUserPasswordDAO;
import com.zs.dao.basic.user.ValidateLoginDAO;
import com.zs.domain.basic.User;
import com.zs.service.basic.user.EditUserPasswordService;
import com.zs.tools.MD5Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/25.
 */
@Service("editUserPasswordService")
public class EditUserPasswordServiceImpl extends EntityServiceImpl<User, EditUserPasswordDAO>
    implements EditUserPasswordService{

    @Resource
    private EditUserPasswordDAO editUserPasswordDAO;
    @Resource
    private ValidateLoginDAO validateLoginDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserPassword(String newPwd, String loginName, String oldPwd) throws Exception {
        //查询登录用户的旧密码是否正确
        User user = validateLoginDAO.validateLogin(loginName, MD5Tools.MD5(oldPwd));
        if(null == user || null == user.getId()){
            throw new BusinessException("旧密码错误");
        }
        editUserPasswordDAO.editUserPassword(MD5Tools.MD5(newPwd), loginName, MD5Tools.MD5(oldPwd));
    }
}
