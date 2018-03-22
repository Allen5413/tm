package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterial.FindTeachMaterialBystmIdDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.service.basic.teachmaterial.FindTeachMaterialBystmIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
@Service("findTeachMaterialBystmIdService")
public class FindTeachMaterialBystmIdServiceImpl extends EntityServiceImpl<TeachMaterial, FindTeachMaterialBystmIdDAO>
    implements FindTeachMaterialBystmIdService{

    @Resource
    private FindTeachMaterialBystmIdDAO findTeachMaterialBystmIdDAO;

    @Override
    public List<TeachMaterial> getTeachMaterialBystmId(Long stmId) {
        return findTeachMaterialBystmIdDAO.getTeachMaterialBystmId(stmId);
    }
}
