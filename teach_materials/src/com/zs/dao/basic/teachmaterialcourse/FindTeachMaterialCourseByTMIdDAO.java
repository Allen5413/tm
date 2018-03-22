package com.zs.dao.basic.teachmaterialcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterialCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/7/11.
 */
public interface FindTeachMaterialCourseByTMIdDAO extends EntityJpaDao<TeachMaterialCourse, Long> {
    @Query("from TeachMaterialCourse where teachMaterialId = ?1")
    public List<TeachMaterialCourse> findTeachMaterialCourseByTMId(long teachMaterialId);
}
