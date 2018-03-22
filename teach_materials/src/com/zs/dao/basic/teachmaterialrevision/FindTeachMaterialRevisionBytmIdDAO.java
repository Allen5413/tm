package com.zs.dao.basic.teachmaterialrevision;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterialRevision;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/6/3.
 */
public interface FindTeachMaterialRevisionBytmIdDAO extends EntityJpaDao<TeachMaterialRevision, Long> {
    @Query("from TeachMaterialRevision where teachMaterialId = ? order by operateTime desc")
    public List<TeachMaterialRevision> getTeachMaterialRevisionBytmId(Long tmId);
}
