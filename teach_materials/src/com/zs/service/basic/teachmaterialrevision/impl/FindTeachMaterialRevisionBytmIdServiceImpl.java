package com.zs.service.basic.teachmaterialrevision.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterialrevision.FindTeachMaterialRevisionBytmIdDAO;
import com.zs.domain.basic.TeachMaterialRevision;
import com.zs.service.basic.teachmaterialrevision.FindTeachMaterialRevisionBytmIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/6/3.
 */
@Service("findTeachMaterialRevisionBytmIdService")
public class FindTeachMaterialRevisionBytmIdServiceImpl extends EntityServiceImpl<TeachMaterialRevision, FindTeachMaterialRevisionBytmIdDAO>
    implements FindTeachMaterialRevisionBytmIdService{

    @Resource
    private FindTeachMaterialRevisionBytmIdDAO findTeachMaterialRevisionBytmIdDAO;

    @Override
    public List<TeachMaterialRevision> findTeachMaterialRevisionBytmId(Long tmId) {
        return findTeachMaterialRevisionBytmIdDAO.getTeachMaterialRevisionBytmId(tmId);
    }
}
