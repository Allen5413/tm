package com.zs.service.sync.eduwestuser.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.eduwestuser.FindEduwestUserByPinDAO;
import com.zs.domain.sync.EduwestUser;
import com.zs.service.sync.eduwestuser.FindEduwestUserByPinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/10.
 */
@Service("findEduwestUserByPinService")
public class FindEduwestUserByPinServiceImpl
        extends EntityServiceImpl<EduwestUser, FindEduwestUserByPinDAO>
        implements FindEduwestUserByPinService{

    @Resource
    private FindEduwestUserByPinDAO findEduwestUserByPinDAO;

    @Override
    public EduwestUser getEduwestUserByPin(String pin) {
        return findEduwestUserByPinDAO.getEduwestUserByPin(pin);
    }
}
