package com.zs.service.basic.user.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.user.UserDAO;
import com.zs.domain.basic.User;
import com.zs.service.basic.user.FindUserPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 实现分页查询用户信息接口
 * Created by Allen on 2015/4/27.
 */
@Service("findUserPageByWhereService")
public class FindUserPageByWhereServiceImpl extends EntityServiceImpl<User, UserDAO>
implements FindUserPageByWhereService {

    @Resource
    public FindPageByWhereDAO findUserPageByWhereDAO;

    @Override
    public PageInfo<User> findPageByWhere(PageInfo<User> pageInfo,
                                           Map<String, String> map, Map<String, Boolean> sortMap) {
        return findUserPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
