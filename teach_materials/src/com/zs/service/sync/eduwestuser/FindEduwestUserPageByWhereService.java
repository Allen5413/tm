package com.zs.service.sync.eduwestuser;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.EduwestUser;

import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
public interface FindEduwestUserPageByWhereService extends EntityService<EduwestUser> {
    public PageInfo<EduwestUser> findPageByWhere(PageInfo<EduwestUser> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
