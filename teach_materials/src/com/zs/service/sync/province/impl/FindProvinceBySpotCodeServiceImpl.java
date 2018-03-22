package com.zs.service.sync.province.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.province.FindProvinceBySpotCodeDAO;
import com.zs.domain.sync.Province;
import com.zs.service.sync.province.FindProvinceBySpotCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/6/1.
 */
@Service("findProvinceBySpotCodeService")
public class FindProvinceBySpotCodeServiceImpl extends EntityServiceImpl<Province, FindProvinceBySpotCodeDAO>
    implements FindProvinceBySpotCodeService {

    @Resource
    private FindProvinceBySpotCodeDAO findProvinceBySpotCodeDAO;

    @Override
    public Province getProvinceBySpotCode(String spotCcode) {
            return findProvinceBySpotCodeDAO.getProvinceBySpotCode(spotCcode);
    }
}
