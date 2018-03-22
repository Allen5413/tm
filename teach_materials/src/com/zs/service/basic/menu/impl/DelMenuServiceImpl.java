package com.zs.service.basic.menu.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.menu.MenuDAO;
import com.zs.dao.basic.resource.FindResourceByMenuIdDAO;
import com.zs.domain.basic.Menu;
import com.zs.domain.basic.Resource;
import com.zs.service.basic.menu.DelMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 实现删除菜单接口
 * Created by Allen on 2015/4/27.
 */
@Service("delMenuService")
public class DelMenuServiceImpl extends EntityServiceImpl<Menu, MenuDAO> implements DelMenuService {

    @javax.annotation.Resource
    private FindResourceByMenuIdDAO findResourceByMenuIdDAO;

    @Override
    public void delMenu(Long id) throws Exception {
        Menu menu = super.get(id);
        if(null != menu){
            //判断菜单下面是否关联得有资源信息，如果有就不能删除
            List<Resource> resources = findResourceByMenuIdDAO.getResourceByMenuID(menu.getId());
            if(null != resources && 0 < resources.size()){
                throw new BusinessException("该菜单下面还有资源，不能被删除！");
            }
            super.remove(menu);
        }
    }
}
