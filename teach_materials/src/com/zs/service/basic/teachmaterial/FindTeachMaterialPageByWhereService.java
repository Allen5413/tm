package com.zs.service.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterial;

import java.util.Map;

/**
 * Created by Allen on 2015/4/29.
 */
public interface FindTeachMaterialPageByWhereService extends EntityService {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
