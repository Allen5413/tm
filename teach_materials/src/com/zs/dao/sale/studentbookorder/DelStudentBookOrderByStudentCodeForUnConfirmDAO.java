package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/7/19.
 */
public interface DelStudentBookOrderByStudentCodeForUnConfirmDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete sbo from student_book_order sbo where sbo.student_code = ?1 and sbo.state = 0")
    public void del(String studentCode);
}
