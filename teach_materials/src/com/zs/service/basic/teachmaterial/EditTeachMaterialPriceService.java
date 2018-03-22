package com.zs.service.basic.teachmaterial;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterial;

/**
 * Created by Allen on 2015/5/20.
 */
public interface EditTeachMaterialPriceService extends EntityService<TeachMaterial> {
    public void editTeachMaterialPrice(TeachMaterial teachMaterial, String loginName);
}
