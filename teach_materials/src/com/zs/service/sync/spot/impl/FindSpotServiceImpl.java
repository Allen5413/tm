package com.zs.service.sync.spot.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.spot.FindSpotAllDAO;
import com.zs.dao.sync.spot.SpotDAO;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
@Service("findSpotService")
public class FindSpotServiceImpl extends EntityServiceImpl<Spot, SpotDAO> implements FindSpotService {

    @Resource
    private FindSpotAllDAO findSpotAllDAO;

    @Override
    public List<Spot> getAll(){
        return findSpotAllDAO.getSpotAll();
    }
}
