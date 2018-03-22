package com.zs.service.basic.menu.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.menu.MenuDAO;
import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.FindMenuPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 实现分页查询菜单信息接口
 * Created by Allen on 2015/4/28.
 */
@Service("findMenuPageByWhereService")
public class FindMenuPageByWhereServiceImpl extends EntityServiceImpl<Menu, MenuDAO>
        implements FindMenuPageByWhereService {

    @Resource
    public FindPageByWhereDAO findMenuPageByWhereDAO;

    @Override
    public PageInfo<Menu> findPageByWhere(PageInfo<Menu> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findMenuPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
