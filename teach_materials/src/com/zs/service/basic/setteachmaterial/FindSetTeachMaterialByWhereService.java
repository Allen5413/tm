package com.zs.service.basic.setteachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SetTeachMaterial;

import java.util.Map;

/**
 * Created by Allen on 2015/4/30.
 */
public interface FindSetTeachMaterialByWhereService extends EntityService {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
