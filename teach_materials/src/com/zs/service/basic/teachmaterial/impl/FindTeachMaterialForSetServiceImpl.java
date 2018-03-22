package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterial.FindTeachMaterialForSetDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialForSetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
@Service("findTeachMaterialForSetService")
public class FindTeachMaterialForSetServiceImpl extends EntityServiceImpl<TeachMaterial, FindTeachMaterialForSetDAO>
    implements FindTeachMaterialForSetService{

    @Resource
    private FindTeachMaterialForSetDAO findTeachMaterialForSetDAO;

    @Override
    public List<TeachMaterial> getTeachMaterialForIsSet() {
        return getTeachMaterialForSet(TeachMaterial.ISSET_YES);
    }

    @Override
    public List<TeachMaterial> getTeachMaterialForIsNotSet() {
        return getTeachMaterialForSet(TeachMaterial.ISSET_NO);
    }

    private List<TeachMaterial> getTeachMaterialForSet(int isSet){
        return findTeachMaterialForSetDAO.getTeachMaterialForSet(isSet);
    }
}
