package com.zs.service.basic.environment;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Environment;

import java.util.Map;

/**
 * Created by LihongZhang on 2015/5/27.
 */
public interface FindEnvironmentPageByWhereService extends EntityService<Environment> {

    public PageInfo<Environment> findPageByWhere(PageInfo<Environment> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
 }
