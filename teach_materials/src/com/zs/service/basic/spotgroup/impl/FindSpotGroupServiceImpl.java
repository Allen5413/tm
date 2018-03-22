package com.zs.service.basic.spotgroup.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotgroup.SpotGroupDAO;
import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.FindSpotGroupService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("findSpotGroupService")
public class FindSpotGroupServiceImpl extends EntityServiceImpl<SpotGroup, SpotGroupDAO> implements FindSpotGroupService {
}
