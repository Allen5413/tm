package com.zs.service.sync.spotwx.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spotwx.FindSpotWxByCodeDAO;
import com.zs.domain.sync.SpotWx;
import com.zs.service.sync.spotwx.FindSpotWxByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2016/9/8.
 */
@Service("findSpotWxByCodeService")
public class FindSpotWxByCodeServiceImpl extends EntityServiceImpl<SpotWx, FindSpotWxByCodeDAO> implements FindSpotWxByCodeService {

    @Resource
    private FindSpotWxByCodeDAO findSpotWxByCodeDAO;

    @Override
    public List<SpotWx> find(String code) {
        return findSpotWxByCodeDAO.find(code);
    }
}
