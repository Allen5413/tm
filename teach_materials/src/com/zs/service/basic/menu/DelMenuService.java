package com.zs.service.basic.menu;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Menu;

/**
 * 删除菜单信息
 * Created by Allen on 2015/4/27.
 */
public interface DelMenuService extends EntityService<Menu> {

    public void delMenu(Long id)throws Exception;
}
