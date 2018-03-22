package com.zs.service.basic.spotgroup.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotgroup.FindSpotGroupByNameDAO;
import com.zs.dao.basic.spotgroup.SpotGroupDAO;
import com.zs.domain.basic.SpotGroup;
import com.zs.service.basic.spotgroup.AddSpotGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/2.
 */
@Service("addSpotGroupService")
public class AddSpotGroupServiceImpl extends EntityServiceImpl<SpotGroup, SpotGroupDAO> implements AddSpotGroupService {

    @Resource
    private FindSpotGroupByNameDAO findSpotGroupByNameDAO;

    @Override
    public void addSpotGroup(SpotGroup spotGroup, String loginName) throws Exception {
        if(null != spotGroup){
            //查询学习中心分组名称是否已经存在
            SpotGroup validSpotGroup = findSpotGroupByNameDAO.getSpotGroupByName(spotGroup.getName(), spotGroup.getSpotCode());
            if(null != validSpotGroup && validSpotGroup.getName().equals(spotGroup.getName())){
                throw new BusinessException("分组名称已经存在！");
            }
            spotGroup.setCreator(loginName);
            spotGroup.setOperator(loginName);
            super.save(spotGroup);
        }
    }
}
