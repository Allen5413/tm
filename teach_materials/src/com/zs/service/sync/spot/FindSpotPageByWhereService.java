package com.zs.service.sync.spot;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Spot;

import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
public interface FindSpotPageByWhereService extends EntityService<Spot> {
    public PageInfo<Spot> findPageByWhere(PageInfo<Spot> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
