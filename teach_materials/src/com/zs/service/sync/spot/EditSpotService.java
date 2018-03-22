package com.zs.service.sync.spot;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Spot;

/**
 * Created by Allen on 2016/3/24.
 */
public interface EditSpotService extends EntityService<Spot> {
    public void edit(Spot spot);
}
