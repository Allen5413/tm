package com.zs.dao.sync.course;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Course;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/19.
 */
public interface FindCourseBytmIdDAO extends EntityJpaDao<Course, Long> {
    @Query(nativeQuery = true, value = "select c.* from sync_course c, teach_material_course tmc where c.code = tmc.course_code and tmc.teach_material_id = ?1")
    public List<Course> getCourseBytmId(Long teachMaterialId);
}
