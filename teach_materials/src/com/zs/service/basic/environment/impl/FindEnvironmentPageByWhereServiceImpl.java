package com.zs.service.basic.environment.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.environment.EnvironmentDAO;
import com.zs.domain.basic.Environment;
import com.zs.service.basic.environment.FindEnvironmentPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by LihongZhang on 2015/5/27.
 */
@Service("findEnvironmentPageByWhere")
public class FindEnvironmentPageByWhereServiceImpl extends EntityServiceImpl<Environment,EnvironmentDAO> implements FindEnvironmentPageByWhereService {

    @Resource
    private FindPageByWhereDAO findEnvironmentByWhereDao;
    @Override
    public PageInfo<Environment> findPageByWhere(PageInfo<Environment> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findEnvironmentByWhereDao.findPageByWhere(pageInfo,map,sortMap);
    }
}
