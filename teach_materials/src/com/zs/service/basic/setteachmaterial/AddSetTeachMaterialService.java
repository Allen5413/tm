package com.zs.service.basic.setteachmaterial;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SetTeachMaterial;

/**
 * Created by Allen on 2015/4/30.
 */
public interface AddSetTeachMaterialService extends EntityService<SetTeachMaterial> {
    public void addSetTeachMaterial(SetTeachMaterial setTeachMaterial, String loginName)throws Exception;
}
