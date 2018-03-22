package com.zs.service.sync.province;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Province;

import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
public interface FindProvincePageByWhereService extends EntityService<Province> {
    public PageInfo<Province> findPageByWhere(PageInfo<Province> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
