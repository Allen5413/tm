package com.zs.service.basic.menu.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.menu.FindMenuByNameDAO;
import com.zs.dao.basic.menu.MenuDAO;
import com.zs.domain.basic.Menu;
import com.zs.service.basic.menu.EditMenuService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现修改菜单接口
 * Created by Allen on 2015/4/27.
 */
@Service("editMenuService")
public class EditMenuServiceImpl extends EntityServiceImpl<Menu, MenuDAO> implements EditMenuService {

    @Resource
    private FindMenuByNameDAO findMenuByNameDAO;

    @Override
    public void editMenu(Menu menu, String loginName) throws Exception {
        if(null != menu) {
            //是否存在
            Menu isExistsMenu = super.get(menu.getId());
            if(null == isExistsMenu){
                throw new BusinessException("菜单不存在！");
            }
            //查询菜单名称是否已经存在
            Menu validMenu = findMenuByNameDAO.getMenuByName(menu.getName());
            if(null != validMenu && validMenu.getName().equals(menu.getName()) && validMenu.getId() != menu.getId()){
                throw new BusinessException("菜单名称已经存在！");
            }
            menu.setOperator(loginName);
            menu.setCreator(isExistsMenu.getCreator());
            menu.setCreateTime(isExistsMenu.getCreateTime());
            super.update(menu);
        }
    }
}
