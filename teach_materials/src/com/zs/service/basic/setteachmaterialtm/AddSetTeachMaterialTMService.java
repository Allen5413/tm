package com.zs.service.basic.setteachmaterialtm;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SetTeachMaterialTM;

/**
 * Created by Allen on 2015/5/21.
 */
public interface AddSetTeachMaterialTMService extends EntityService<SetTeachMaterialTM> {

    public void addSetTeachMaterialTM(Long setTeachMaterialId, String tmIds, String loginName)throws Exception;
}
