package com.zs.service.basic.environment.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.environment.FindEnvironmentByCodeDAO;
import com.zs.domain.basic.Environment;
import com.zs.service.basic.environment.FindEnvironmentByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/13.
 */
@Service("findEnvironmentByCodeService")
public class FindEnvironmentByCodeServiceImpl extends EntityServiceImpl<Environment, FindEnvironmentByCodeDAO> implements FindEnvironmentByCodeService{

    @Resource
    private FindEnvironmentByCodeDAO findEnvironmentByCodeDAO;

    @Override
    public Environment getEnvironmentByCode(String code) {
        return findEnvironmentByCodeDAO.getEnvironmentByCode(code);
    }
}
