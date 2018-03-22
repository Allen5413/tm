package com.zs.service.sync.spot.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/10.
 */
@Service("findSpotByCodeService")
public class FindSpotByCodeServiceImpl
    extends EntityServiceImpl<Spot, FindSpotByCodeDAO>
    implements FindSpotByCodeService{

    @Resource
    private FindSpotByCodeDAO findSpotByCodeDAO;

    @Override
    public Spot getSpotByCode(String code) {
        return findSpotByCodeDAO.getSpotByCode(code);
    }
}
