package com.zs.service.basic.teachmaterialtype;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialType;

/**
 * Created by Allen on 2015/5/3.
 */
public interface DelTeachMaterialTypeService extends EntityService<TeachMaterialType> {

    public void delTeachMaterialType(Long id)throws Exception;
}
