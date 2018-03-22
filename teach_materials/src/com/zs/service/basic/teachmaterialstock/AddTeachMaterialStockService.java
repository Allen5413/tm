package com.zs.service.basic.teachmaterialstock;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialStock;

/**
 * Created by Allen on 2015/8/11.
 */
public interface AddTeachMaterialStockService extends EntityService<TeachMaterialStock> {

    public void add(TeachMaterialStock teachMaterialStock, String loginName)throws Exception;
}
