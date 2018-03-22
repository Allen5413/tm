package com.zs.dao.sale.studentbookorderlog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/7/19.
 */
public interface DelStudentBookOrderLogByStudentCodeForUnConfirmDAO extends EntityJpaDao<StudentBookOrderLog, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete sbol " +
            "from student_book_order_log sbol INNER JOIN student_book_order sbo on sbo.order_code = sbol.order_code " +
            "where sbo.student_code = ?1 and sbo.state = 0")
    public void del(String studentCode);
}
