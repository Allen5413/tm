package com.zs.dao.basic.teachmaterialcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterialCourse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/7/16.
 */
public interface DelTeachMaterialCourseBytmIdAndCourseCodeDAO extends EntityJpaDao<TeachMaterialCourse, Long> {
    @Modifying
    @Query("delete from TeachMaterialCourse where teachMaterialId = ?1 and courseCode = ?2")
    public void delTeachMaterialCourseBytmIdAndCourseCode(Long teachMaterialId, String courseCode);
}
