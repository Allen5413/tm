package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/6.
 */
public interface FindTeachMaterialByRevisionDAO extends EntityJpaDao<TeachMaterial,Long> {
    @Query("from TeachMaterial where revision = ?1")
    public List<TeachMaterial> getTeachMaterialByRevision(String revision);
}
