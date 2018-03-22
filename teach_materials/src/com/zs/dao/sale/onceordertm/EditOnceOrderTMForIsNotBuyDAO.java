package com.zs.dao.sale.onceordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 当确认普通订单时，要把学生确认的课程修改为已经购买过该课程
 * Created by Allen on 2016/7/19.
 */
public interface EditOnceOrderTMForIsNotBuyDAO extends EntityJpaDao<StudentBookOnceOrderTM, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "update student_book_once_order_tm sbootm " +
            "INNER JOIN (select sboo.id, t.* from student_book_once_order sboo, " +
            "(select sbo.student_code, sbotm.course_code from student_book_order sbo, student_book_order_tm sbotm " +
            "where sbo.order_code = sbotm.order_code and sbo.order_code = ?1 and sbotm.count > 0) t " +
            "where sboo.student_code = t.student_code" +
            ") tt on sbootm.order_id = tt.id and sbootm.course_code = tt.course_code " +
            "set sbootm.is_buy = 0, sbootm.count = 1")
    public void editor(String orderCode);
}
