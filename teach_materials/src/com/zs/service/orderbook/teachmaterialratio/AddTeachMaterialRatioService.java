package com.zs.service.orderbook.teachmaterialratio;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.orderbook.TeachMaterialRatio;

/**
 * Created by Allen on 2015/5/5.
 */
public interface AddTeachMaterialRatioService extends EntityService<TeachMaterialRatio> {
    public void addTeachMaterialRatio(TeachMaterialRatio teachMaterialRatio, String loginName)throws Exception;
}
