package com.zs.service.basic.teachmaterialtype;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialType;

/**
 * Created by Allen on 2015/5/3.
 */
public interface EditTeachMaterialTypeService extends EntityService<TeachMaterialType> {

    public void editTeachMaterialType(TeachMaterialType teachMaterialType, String loginName)throws Exception;
}
