package com.zs.service.sync.province.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.province.FindProvinceAllDAO;
import com.zs.dao.sync.province.ProvinceDAO;
import com.zs.domain.sync.Province;
import com.zs.service.sync.province.FindProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
@Service("findProvinceService")
public class FindProvinceServiceImpl extends EntityServiceImpl<Province, ProvinceDAO> implements FindProvinceService {

    @Resource
    private FindProvinceAllDAO findProvinceAllDAO;

    @Override
    public List<Province> getAll(){
        return findProvinceAllDAO.getProvinceAll();
    }
}
