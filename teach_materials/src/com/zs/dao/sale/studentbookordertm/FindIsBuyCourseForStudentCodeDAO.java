package com.zs.dao.sale.studentbookordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderTM;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/10/13.
 */
public interface FindIsBuyCourseForStudentCodeDAO extends EntityJpaDao<StudentBookOrderTM, Long> {
    @Query(nativeQuery = true, value = "select DISTINCT sbotm.course_code from student_book_order sbo, student_book_order_tm sbotm " +
            "where sbo.order_code = sbotm.order_code and sbo.state > 0 and sbotm.count > 0 " +
            "and sbo.student_code = ?1")
    public List<String> find(String studentCode);
}
