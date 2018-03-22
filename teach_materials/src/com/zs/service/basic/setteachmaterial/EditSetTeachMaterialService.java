package com.zs.service.basic.setteachmaterial;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SetTeachMaterial;

/**
 * Created by Allen on 2015/5/4.
 */
public interface EditSetTeachMaterialService extends EntityService<SetTeachMaterial> {
    public void editSetTeachMaterial(SetTeachMaterial setTeachMaterial, String loginName)throws Exception;
}
