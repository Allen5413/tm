package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/19.
 */
public interface FindTeachMaterialByTypeIdDAO extends EntityJpaDao<TeachMaterial,Long> {
    @Query("from TeachMaterial where teachMaterialTypeId = ?1")
    public List<TeachMaterial> getTeachMaterialByTypeId(Long teachMaterialTypeId);
}
