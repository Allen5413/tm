package com.zs.service.basic.teachmaterial;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/8/13.
 */
public interface DownTeachMaterialForNotStockService extends EntityService {
    public String down(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap, String fileName)throws Exception;
}
