package com.zs.dao.basic.courseteachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.CourseTeachMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/12/14 0014.
 */
public interface DelCourseTeachMaterialBySemesterIdDAO extends EntityJpaDao<CourseTeachMaterial,Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete from course_teach_material where semester_id = ?1")
    public void del(long semesterId);

    /**
     * 在重新添加课程对应教材后，要把一门课程有多条对应记录的，教材id又为null给删掉
     * @param semesterId
     */
    @Modifying
    @Query(nativeQuery = true, value = "delete ctm from course_teach_material ctm INNER JOIN (" +
            "SELECT course_code " +
            "FROM course_teach_material ctm2 where ctm2.semester_id = ?1 " +
            "group by course_code having(count(*)>1) " +
            ") t on ctm.course_code = t.course_code where ctm.semester_id = ?1 and ctm.teach_material_id is null")
    public void delTMIdIsNullForMoreCourse(long semesterId);
}
