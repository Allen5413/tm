package com.zs.service.sync.province.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.province.FindProvinceByCodeDAO;
import com.zs.domain.sync.Province;
import com.zs.service.sync.province.FindProvinceByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/10.
 */
@Service("findProvinceByCodeService")
public class FindProvinceByCodeServiceImpl
    extends EntityServiceImpl<Province, FindProvinceByCodeDAO>
    implements FindProvinceByCodeService{

    @Resource
    private FindProvinceByCodeDAO findProvinceByCodeDAO;

    @Override
    public Province getProvinceByCode(String code) {
        return findProvinceByCodeDAO.getProvinceByCode(code);
    }
}
