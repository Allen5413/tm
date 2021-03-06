package com.zs.dao.sale.onceordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOnceOrderTM;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询一个学生未发出的订单，还没发出的，总价
 * Created by Allen on 2017/3/3.
 */
public interface FindOnceTMPriceByStudentCodeForNotSendOnceOrderDAO extends EntityJpaDao<StudentBookOnceOrderTM, Long> {
    @Query(nativeQuery = true, value = "select ifnull(sum(sbotm.count*sbotm.price),0) from student_book_once_order sbo, student_book_once_order_tm sbotm " +
            "where sbo.id = sbotm.order_id and sbo.state BETWEEN 0 and 4 and sbo.student_code = ?1 and sbo.semester_id = ?2")
    public double find(String studentCode, long semesterId);
}
