package com.zs.dao.sync.spec;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SpecCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/10/20.
 */
public interface FindSpecCourseTMBySpecAndLevelDAO extends EntityJpaDao<SpecCourse, Long> {

    @Query(nativeQuery = true, value = "select DISTINCT t.course_code, t.courseName, tt.tmName, tt.isbn, tt.author, tt.pName pName, tt.price from " +
            "(select sp.spec_code, s.name, sp.course_code, c.name courseName, sp.level_code " +
            "from sync_spec_course sp LEFT JOIN sync_spec s on sp.spec_code = s.code " +
            "LEFT JOIN sync_course c on sp.course_code = c.code " +
            ") t LEFT JOIN (select tmc.course_code, tm.id, tm.`name` tmName, tm.author, p.name pName, tm.isbn, tm.price, tm.press_year, tm.revision " +
            "from teach_material tm, teach_material_course tmc, press p where tm.id = tmc.teach_material_id and tm.press_id = p.id and tm.state = 0) tt on t.course_code = tt.course_code " +
            "where t.spec_code = ?1 and t.level_code = ?2")
    public List<Object[]> findTMBySpecLevel(String specCode, String levelCode);

    @Query(nativeQuery = true, value = "select sp.course_code, c.name, tm.name tmName, tm.isbn, tm.author, p.name pName, tm.price " +
            "from " +
            "sync_spec_course sp LEFT JOIN sync_course c on sp.course_code = c.code " +
            "LEFT JOIN set_teach_material stm on c.code = stm.buy_course_code " +
            "LEFT JOIN set_teach_material_tm stmtm on stm.id = stmtm.set_teach_material_id " +
            "LEFT JOIN teach_material tm on stmtm.teach_material_id = tm.id and tm.state = 0 " +
            "LEFT JOIN press p on tm.press_id = p.id " +
            "where sp.spec_code = ?1 and sp.level_code = ?2")
    public List<Object[]> findSTMBySpecLevel(String specCode, String levelCode);


}
