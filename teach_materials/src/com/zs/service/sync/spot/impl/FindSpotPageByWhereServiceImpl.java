package com.zs.service.sync.spot.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.sync.spot.SpotDAO;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Service("findSpotPageByWhereService")
public class FindSpotPageByWhereServiceImpl extends EntityServiceImpl<Spot, SpotDAO>
        implements FindSpotPageByWhereService{

    @Resource
    public FindPageByWhereDAO findSpotPageByWhereDAO;

    @Override
    public PageInfo<Spot> findPageByWhere(PageInfo<Spot> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findSpotPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
