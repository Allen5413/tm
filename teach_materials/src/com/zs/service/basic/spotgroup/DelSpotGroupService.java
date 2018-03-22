package com.zs.service.basic.spotgroup;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SpotGroup;

/**
 * Created by Allen on 2015/5/2.
 */
public interface DelSpotGroupService extends EntityService<SpotGroup> {
    public void delSpotGroup(Long id)throws Exception;
}
