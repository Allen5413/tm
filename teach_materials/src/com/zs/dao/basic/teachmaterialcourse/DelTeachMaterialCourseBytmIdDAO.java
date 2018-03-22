package com.zs.dao.basic.teachmaterialcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterialCourse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/19.
 */
public interface DelTeachMaterialCourseBytmIdDAO extends EntityJpaDao<TeachMaterialCourse, Long> {
    @Modifying
    @Query("delete from TeachMaterialCourse where teachMaterialId = ?1")
    public void delTeachMaterialCourseBytmId(Long teachMaterialId);
}
