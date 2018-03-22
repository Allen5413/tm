package com.zs.dao.sale.onceordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/7/17.
 */
public interface DelOnceOrderTMByTMIdAndCourseCodeDAO extends EntityJpaDao<StudentBookOnceOrderTM, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "DELETE student_book_once_order_tm FROM student_book_once_order_tm, student_book_once_order " +
            "WHERE student_book_once_order.state < ?1 AND student_book_once_order.id = student_book_once_order_tm.order_id " +
            "AND student_book_once_order_tm.teach_material_id = ?2 AND student_book_once_order_tm.course_code = ?3")
    public void del(int state, long tmId, String courseCode);
}
