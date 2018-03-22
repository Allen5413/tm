package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
public interface FindTeachMaterialByPressIdDAO extends EntityJpaDao<TeachMaterial, Long> {
    @Query("from TeachMaterial where pressId = ?1")
    public List<TeachMaterial> getTeachMaterialByPressId(Long pressId);
}
