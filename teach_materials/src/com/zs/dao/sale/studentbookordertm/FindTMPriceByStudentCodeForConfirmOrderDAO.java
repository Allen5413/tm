package com.zs.dao.sale.studentbookordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrderTM;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询一个学生已经确认了的订单，还没发出的，总价
 * Created by Allen on 2015/10/28.
 */
public interface FindTMPriceByStudentCodeForConfirmOrderDAO extends EntityJpaDao<StudentBookOrderTM, Long> {
    @Query(nativeQuery = true, value = "select ifnull(sum(sbotm.count*sbotm.price),0) from student_book_order sbo, student_book_order_tm sbotm " +
            "where sbo.order_code = sbotm.order_code and sbo.state BETWEEN 1 and 3 and sbo.student_code = ?1")
    public double find(String studentCode);
}
