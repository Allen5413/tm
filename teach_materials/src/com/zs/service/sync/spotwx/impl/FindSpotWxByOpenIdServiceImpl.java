package com.zs.service.sync.spotwx.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spotwx.FindSpotWxByOpenIdDAO;
import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.FindSpotWxByOpenIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/5/24.
 */
@Service("findSpotWxByOpenIdService")
public class FindSpotWxByOpenIdServiceImpl extends EntityServiceImpl<SpotWx, FindSpotWxByOpenIdDAO> implements FindSpotWxByOpenIdService {

    @Resource
    private FindSpotWxByOpenIdDAO findSpotWxByOpenIdDAO;

    @Override
    public SpotWx find(String openId) {
        return findSpotWxByOpenIdDAO.find(openId);
    }
}
