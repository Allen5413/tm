package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询学生还在处理的订单信息，学生登录页面查询用
 * Created by Allen on 2015/10/22.
 */
public interface FindStudentBookOrderByStudentCodeForStudentInfoDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Query(nativeQuery = true, value = "SELECT sbo.id, sbo.order_code, sbo.state, sum(sbotm.count), sum(sbotm.count * sbotm.price), sbop.logistic_code, kp.data, sbo.create_time FROM student_book_order sbo " +
            "INNER JOIN student_book_order_tm sbotm on sbo.order_code = sbotm.order_code " +
            "LEFT JOIN student_book_order_package sbop on sbo.package_id = sbop.id " +
            "LEFT JOIN kuaidi_push kp on sbop.logistic_code = kp.nu " +
            "WHERE sbo.student_code = ?1 and sbotm.count > 0 and sbo.state BETWEEN 1 and 4 " +
            "GROUP BY sbo.id ORDER BY sbo.create_time DESC")
    public List<Object[]> find(String studentCode);

}
