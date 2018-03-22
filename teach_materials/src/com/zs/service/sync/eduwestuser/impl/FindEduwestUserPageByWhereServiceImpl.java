package com.zs.service.sync.eduwestuser.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.sync.eduwestuser.EduwestUserDAO;
import com.zs.domain.sync.EduwestUser;
import com.zs.service.sync.eduwestuser.FindEduwestUserPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Service("findEduwestUserPageByWhereService")
public class FindEduwestUserPageByWhereServiceImpl extends EntityServiceImpl<EduwestUser, EduwestUserDAO>
    implements FindEduwestUserPageByWhereService{

    @Resource
    public FindPageByWhereDAO findEduwestUserPageByWhereDAO;

    @Override
    public PageInfo<EduwestUser> findPageByWhere(PageInfo<EduwestUser> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findEduwestUserPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
