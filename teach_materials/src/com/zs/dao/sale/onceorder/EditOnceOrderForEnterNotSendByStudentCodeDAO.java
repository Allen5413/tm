package com.zs.dao.sale.onceorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2018/9/6.
 */
public interface EditOnceOrderForEnterNotSendByStudentCodeDAO extends EntityJpaDao<StudentBookOnceOrder, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "update student_book_once_order set state = 0 where student_code = ?1 and state < 4")
    public void edit(String studentCode);
}
