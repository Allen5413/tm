package com.zs.service.sync.spotwx.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spotwx.FindSpotWxByIdCardDAO;
import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.FindSpotWxByIdCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/9/8.
 */
@Service("findSpotWxByIdCardService")
public class FindSpotWxByIdCardServiceImpl extends EntityServiceImpl<SpotWx, FindSpotWxByIdCardDAO> implements FindSpotWxByIdCardService {

    @Resource
    private FindSpotWxByIdCardDAO findSpotWxByIdCardDAO;

    @Override
    public SpotWx find(String idCard) {
        return findSpotWxByIdCardDAO.find(idCard);
    }
}
