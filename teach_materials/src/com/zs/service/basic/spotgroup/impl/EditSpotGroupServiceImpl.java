package com.zs.service.basic.spotgroup.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotgroup.FindSpotGroupByNameDAO;
import com.zs.dao.basic.spotgroup.SpotGroupDAO;
import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.EditSpotGroupService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/2.
 */
@Service("editSpotGroupService")
public class EditSpotGroupServiceImpl extends EntityServiceImpl<SpotGroup, SpotGroupDAO> implements EditSpotGroupService {

    @Resource
    private FindSpotGroupByNameDAO findSpotGroupByNameDAO;

    @Override
    public void editSpotGroup(SpotGroup spotGroup, String loginName) throws Exception {
        if(null != spotGroup) {
            //是否存在
            SpotGroup isExistsSpotGroup = super.get(spotGroup.getId());
            if(null == isExistsSpotGroup){
                throw new BusinessException("学习中心分组不存在！");
            }
            //查询分组名称是否已经存在
            SpotGroup validSpotGroup = findSpotGroupByNameDAO.getSpotGroupByName(spotGroup.getName(), spotGroup.getSpotCode());
            if(null != validSpotGroup && validSpotGroup.getName().equals(spotGroup.getName()) && validSpotGroup.getId() != spotGroup.getId()){
                throw new BusinessException("菜单名称已经存在！");
            }
            spotGroup.setOperator(loginName);
            spotGroup.setCreateTime(isExistsSpotGroup.getCreateTime());
            super.update(spotGroup);
        }
    }
}
