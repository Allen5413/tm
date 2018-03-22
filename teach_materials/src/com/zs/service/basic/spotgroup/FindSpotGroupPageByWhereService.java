package com.zs.service.basic.spotgroup;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SpotGroup;

import java.util.Map;

/**
 * Created by Allen on 2015/5/3.
 */
public interface FindSpotGroupPageByWhereService extends EntityService<SpotGroup> {
    public PageInfo<SpotGroup> findPageByWhere(PageInfo<SpotGroup> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
