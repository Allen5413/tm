package com.zs.dao.sale.studentbookordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 删除一个学生还没有确认的订单明细
 * Created by Allen on 2016/7/19.
 */
public interface DelStudentBookOrderTMByStudentCodeForUnConfirmDAO extends EntityJpaDao<StudentBookOrderTM, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete sbotm from " +
            "student_book_order_tm sbotm INNER JOIN student_book_order sbo on sbo.order_code = sbotm.order_code " +
            "where sbo.student_code = ?1 and sbo.state = 0")
    public void del(String studentCode);
}
