package com.zs.service.sync.province.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.sync.province.ProvinceDAO;
import com.zs.domain.sync.Province;
import com.zs.service.sync.province.FindProvincePageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Service("findProvincePageByWhereService")
public class FindProvincePageByWhereServiceImpl extends EntityServiceImpl<Province, ProvinceDAO>
        implements FindProvincePageByWhereService {

    @Resource
    public FindPageByWhereDAO findProvincePageByWhereDAO;

    @Override
    public PageInfo<Province> findPageByWhere(PageInfo<Province> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findProvincePageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
