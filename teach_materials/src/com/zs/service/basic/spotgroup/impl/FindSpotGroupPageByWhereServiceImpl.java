package com.zs.service.basic.spotgroup.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.spotgroup.SpotGroupDAO;
import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.FindSpotGroupPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("findSpotGroupPageByWhereService")
public class FindSpotGroupPageByWhereServiceImpl extends EntityServiceImpl<SpotGroup, SpotGroupDAO>
        implements FindSpotGroupPageByWhereService {
    @Resource
    public FindPageByWhereDAO findSpotGroupPageByWhereDAO;

    @Override
    public PageInfo<SpotGroup> findPageByWhere(PageInfo<SpotGroup> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findSpotGroupPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
