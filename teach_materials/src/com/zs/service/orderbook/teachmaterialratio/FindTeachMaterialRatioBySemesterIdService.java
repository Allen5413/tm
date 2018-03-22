package com.zs.service.orderbook.teachmaterialratio;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.orderbook.TeachMaterialRatio;

/**
 * Created by Allen on 2015/5/6.
 */
public interface FindTeachMaterialRatioBySemesterIdService extends EntityService<TeachMaterialRatio> {
    public TeachMaterialRatio getTeachMaterialRatioBySemesterId(Long semesterId);
}
