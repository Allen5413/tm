package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询一门教材所关联了哪些课程
 * Created by Allen on 2016/11/4.
 */
public interface FindCourseByTMIdDAO extends EntityJpaDao<TeachMaterial, Long> {
    @Query(nativeQuery = true, value = "select tmc.course_code from teach_material_course tmc where tmc.teach_material_id = ?1 " +
            "union " +
            "select stm.buy_course_code from set_teach_material_tm stmtm, set_teach_material stm where stmtm.set_teach_material_id = stm.id and stmtm.teach_material_id = ?1")
    public List<String> find(long tmId);
}
