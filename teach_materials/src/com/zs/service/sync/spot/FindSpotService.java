package com.zs.service.sync.spot;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Spot;

import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
public interface FindSpotService extends EntityService<Spot> {
    public List<Spot> getAll();
    
}
