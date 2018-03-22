package com.zs.service.basic.teachmaterial;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterial;

import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
public interface FindTeachMaterialForSetService extends EntityService<TeachMaterial> {
    public List<TeachMaterial> getTeachMaterialForIsSet();

    public List<TeachMaterial> getTeachMaterialForIsNotSet();
}
