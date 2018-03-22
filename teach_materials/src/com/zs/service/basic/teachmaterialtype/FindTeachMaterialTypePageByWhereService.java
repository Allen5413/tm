package com.zs.service.basic.teachmaterialtype;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialType;

import java.util.Map;

/**
 * Created by Allen on 2015/5/3.
 */
public interface FindTeachMaterialTypePageByWhereService extends EntityService<TeachMaterialType> {
    public PageInfo<TeachMaterialType> findPageByWhere(PageInfo<TeachMaterialType> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
