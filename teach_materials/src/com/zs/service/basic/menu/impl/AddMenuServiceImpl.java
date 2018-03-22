package com.zs.service.basic.menu.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.menu.FindMenuByNameDAO;
import com.zs.dao.basic.menu.MenuDAO;
import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.AddMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现新增菜单接口
 * Created by Allen on 2015/4/27.
 */
@Service("addMenuService")
public class AddMenuServiceImpl extends EntityServiceImpl<Menu, MenuDAO> implements AddMenuService {

    @Resource
    private FindMenuByNameDAO findMenuByNameDAO;

    @Override
    public void addMenu(Menu menu, String loginName) throws Exception {
        if(null != menu){
            //查询菜单名称是否已经存在
            Menu validMenu = findMenuByNameDAO.getMenuByName(menu.getName());
            if(null != validMenu && validMenu.getName().equals(menu.getName())){
                throw new BusinessException("菜单名称已经存在！");
            }
            menu.setCreator(loginName);
            menu.setOperator(loginName);
            super.save(menu);
        }
    }
}
