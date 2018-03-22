package com.zs.service.basic.menu;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Menu;

/**
 * 新增菜单信息
 * Created by Allen on 2015/4/27.
 */
public interface AddMenuService extends EntityService<Menu> {

    public void addMenu(Menu menu, String loginName)throws Exception;
}
