package com.zs.service.basic.menu;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Menu;

import java.util.Map;

/**
 * 分页查询菜单信息
 * Created by Allen on 2015/4/28.
 */
public interface FindMenuPageByWhereService extends EntityService<Menu> {
    public PageInfo<Menu> findPageByWhere(PageInfo<Menu> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
