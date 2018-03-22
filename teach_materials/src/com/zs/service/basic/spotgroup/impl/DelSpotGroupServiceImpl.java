package com.zs.service.basic.spotgroup.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotgroup.SpotGroupDAO;
import com.zs.domain.basic.SpotGroup;
import com.zs.domain.basic.SpotGroupStudent;
import com.zs.service.basic.spotgroup.DelSpotGroupService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Allen on 2015/5/2.
 */
@Service("delSpotGroupService")
public class DelSpotGroupServiceImpl extends EntityServiceImpl<SpotGroup, SpotGroupDAO> implements DelSpotGroupService {
    @Override
    public void delSpotGroup(Long id) throws Exception {
        SpotGroup spotGroup = super.get(id);
        if(null != spotGroup){
            //判断菜单下面是否关联得有资源信息，如果有就不能删除
            Set<SpotGroupStudent> spotGroupStudents = spotGroup.getSpotGroupStudents();
            if(null != spotGroupStudents && 0 < spotGroupStudents.size()){
                throw new BusinessException("该分组下面还有学生，不能被删除！");
            }
            super.remove(spotGroup);
        }
    }
}
