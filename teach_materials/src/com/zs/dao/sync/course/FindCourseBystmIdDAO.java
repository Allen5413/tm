package com.zs.dao.sync.course;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Course;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/20.
 */
public interface FindCourseBystmIdDAO extends EntityJpaDao<Course, Long> {
    @Query(nativeQuery = true, value = "select c.* from sync_course c, set_teach_material_course stmc where c.code = stmc.course_code and stmc.set_teach_material_id = ?1")
    public List<Course> getCourseBystmId(Long setTeachMaterialId);
}
