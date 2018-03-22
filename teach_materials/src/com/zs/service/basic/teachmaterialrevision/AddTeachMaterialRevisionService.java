package com.zs.service.basic.teachmaterialrevision;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialRevision;

/**
 * Created by Allen on 2015/6/3.
 */
public interface AddTeachMaterialRevisionService extends EntityService<TeachMaterialRevision> {
    public void addTeachMaterialRevision(TeachMaterialRevision teachMaterialRevision, String loginName)throws Exception;
}
