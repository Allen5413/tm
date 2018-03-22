package com.zs.service.sync.spot;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Spot;

/**
 * Created by Allen on 2016/3/24.
 */
public interface AddSpotService extends EntityService<Spot> {
    public void add(Spot spot);
}
