package com.zs.service.basic.resource;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Resource;

import java.util.Map;

/**
 * 分页查询资源信息
 * Created by Allen on 2015/4/28.
 */
public interface FindResourcePageByWhereService extends EntityService<Resource> {
    public PageInfo<Resource> findPageByWhere(PageInfo<Resource> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
