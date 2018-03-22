package com.zs.dao.basic.courseteachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.CourseTeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/12/14 0014.
 */
public interface FindCourseTeachMaterialForAddDAO extends EntityJpaDao<CourseTeachMaterial,Long> {
    @Query(nativeQuery = true, value = "select DISTINCT tt.code, tt.teach_material_id from(" +
            "select t.code, tmc.teach_material_id from " +
            "(select DISTINCT c.code, c.name from semester s, sync_course c, sync_begin_schedule bs " +
            "where s.year = bs.academic_year and s.quarter = bs.term and bs.course_code = c.code " +
            "and s.id = ?1) t LEFT JOIN teach_material_course tmc on t.code = tmc.course_code " +
            "UNION ALL " +
            "select t.code, stmtm.teach_material_id FROM " +
            "(select DISTINCT c.code, c.name from semester s, sync_course c, sync_begin_schedule bs " +
            "where s.year = bs.academic_year and s.quarter = bs.term and bs.course_code = c.code " +
            "and s.id = ?1) t LEFT JOIN set_teach_material stm on t.code = stm.buy_course_code " +
            "INNER JOIN set_teach_material_tm stmtm on stm.id = stmtm.set_teach_material_id" +
            ") tt order by code")
    public List<Object[]> find(long semesterId);
}
