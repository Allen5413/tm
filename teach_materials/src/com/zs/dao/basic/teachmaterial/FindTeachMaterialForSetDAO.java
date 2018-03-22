package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
public interface FindTeachMaterialForSetDAO extends EntityJpaDao<TeachMaterial,Long> {
    @Query("from TeachMaterial where isSet = ?1 order by isbn")
    public List<TeachMaterial> getTeachMaterialForSet(int isSet);
}
