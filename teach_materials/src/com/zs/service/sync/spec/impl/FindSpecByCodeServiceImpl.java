package com.zs.service.sync.spec.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spec.SpecDAO;
import com.zs.domain.sync.Spec;
import com.zs.service.sync.spec.FindSpecByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/8/4.
 */
@Service("findSpecByCodeService")
public class FindSpecByCodeServiceImpl extends EntityServiceImpl<Spec, SpecDAO> implements FindSpecByCodeService {

    @Resource
    private SpecDAO specDAO;

    @Override
    public Spec findSpecByCode(String code) {
        return specDAO.querySpecByCode(code);
    }
}
