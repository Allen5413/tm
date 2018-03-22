package com.zs.service.basic.teachmaterial;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterial;

/**
 * Created by Allen on 2015/4/29.
 */
public interface AddTeachMaterialService extends EntityService<TeachMaterial> {
    public void addTeachMaterial(TeachMaterial teachMaterial, String courseCode, String loginName)throws Exception;
}
