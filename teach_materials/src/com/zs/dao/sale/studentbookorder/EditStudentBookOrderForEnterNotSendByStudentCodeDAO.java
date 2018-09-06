package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2018/9/6.
 */
public interface EditStudentBookOrderForEnterNotSendByStudentCodeDAO extends EntityJpaDao<StudentBookOrder, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "update student_book_order set state = 0 where student_code = ?1 and state < 3")
    public void edit(String studentCode);
}
