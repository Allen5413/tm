package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询最新的订单信息，用于微信推送
 * Created by Allen on 2016/5/24 0024.
 */
public interface FindStudentBookOrderForWXByCodeDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Query(nativeQuery = true, value = "select ttt.* from ( " +
            "select tt.* FROM " +
            "( " +
            "select t.*, sum(sbotm.count) count, 0 " +
            "FROM " +
            "( " +
            "SELECT sbo.id, sbo.order_code, sbo.state, ifnull(sbop.logistic_code, '') logistic_code, max(sbo.create_time) create_time " +
            "FROM student_book_order sbo LEFT JOIN student_book_order_package sbop ON sbo.package_id = sbop.id  " +
            "WHERE sbo.state > 0 AND sbo.student_code = ?1  " +
            ") t, student_book_order_tm sbotm  " +
            "WHERE t.order_code = sbotm.order_code  " +
            "GROUP BY id, order_code, state, logistic_code, create_time  " +
            ") tt WHERE tt.count > 0 " +
            " " +
            "UNION ALL " +
            " " +
            "select tt.* FROM " +
            "( " +
            "select t.*, sum(sbotm.count) count, 1 " +
            "FROM " +
            "( " +
            "SELECT sbo.id, sbo.order_code, sbo.state, ifnull(sbop.logistic_code, '') logistic_code, max(sbo.create_time) create_time " +
            "FROM student_book_once_order sbo LEFT JOIN student_book_order_package sbop ON sbo.package_id = sbop.id  " +
            "WHERE sbo.state > 0 AND sbo.student_code = ?1  " +
            ") t, student_book_once_order_tm sbotm  " +
            "WHERE t.id = sbotm.order_id  " +
            "GROUP BY id, order_code, state, logistic_code, create_time  " +
            ") tt WHERE tt.count > 0 " +
            ") ttt order by ttt.create_time desc limit 1;")
    public List<Object[]> find(String code);
}
