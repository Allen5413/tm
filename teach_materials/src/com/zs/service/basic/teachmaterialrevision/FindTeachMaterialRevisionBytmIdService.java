package com.zs.service.basic.teachmaterialrevision;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialRevision;

import java.util.List;

/**
 * Created by Allen on 2015/6/3.
 */
public interface FindTeachMaterialRevisionBytmIdService extends EntityService<TeachMaterialRevision> {
    public List<TeachMaterialRevision> findTeachMaterialRevisionBytmId(Long tmId);
}
