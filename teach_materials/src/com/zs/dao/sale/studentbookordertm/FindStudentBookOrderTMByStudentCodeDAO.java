package com.zs.dao.sale.studentbookordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderTM;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/10/20.
 */
public interface FindStudentBookOrderTMByStudentCodeDAO extends EntityJpaDao<StudentBookOrderTM, Long> {

    @Query(nativeQuery = true, value = "select sbotm.id, sbotm.order_code, sbotm.course_code, c.name courseName, tm.name tmName, sbotm.price, sbotm.count " +
            "FROM student_book_order sbo " +
            "INNER JOIN student_book_order_tm sbotm ON sbo.order_code = sbotm.order_code " +
            "LEFT JOIN sync_course c ON sbotm.course_code = c.code " +
            "LEFT JOIN teach_material tm ON sbotm.teach_material_id = tm.id " +
            "WHERE sbo.state = ?1 and sbotm.count > 0 and sbo.student_code = ?2")
    public List<Object[]> find(int state, String studentCode);
}
