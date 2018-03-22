package com.zs.service.basic.menu;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Menu;

/**
 * 编辑菜单信息
 * Created by Allen on 2015/4/27.
 */
public interface EditMenuService extends EntityService<Menu> {

    public void editMenu(Menu menu, String loginName)throws Exception;
}
