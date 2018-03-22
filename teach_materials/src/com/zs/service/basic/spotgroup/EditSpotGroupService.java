package com.zs.service.basic.spotgroup;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SpotGroup;

/**
 * Created by Allen on 2015/5/2.
 */
public interface EditSpotGroupService extends EntityService<SpotGroup> {
    public void editSpotGroup(SpotGroup spotGroup, String loginName)throws Exception;
}
