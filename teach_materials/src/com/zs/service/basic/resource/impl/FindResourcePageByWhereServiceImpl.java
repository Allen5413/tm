package com.zs.service.basic.resource.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.resource.ResourceDAO;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.resource.FindResourcePageByWhereService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 实现分页查询资源信息接口
 * Created by Allen on 2015/4/28.
 */
@Service("findResourcePageByWhereService")
public class FindResourcePageByWhereServiceImpl extends EntityServiceImpl<Resource, ResourceDAO>
        implements FindResourcePageByWhereService {

    @javax.annotation.Resource
    public FindPageByWhereDAO findResourcePageByWhereDAO;

    @Override
    public PageInfo<Resource> findPageByWhere(PageInfo<Resource> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findResourcePageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
