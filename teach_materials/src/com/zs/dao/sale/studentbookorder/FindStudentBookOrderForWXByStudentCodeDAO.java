package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询一个学生的所有有效订单，微信端用
 * Created by Allen on 2016/5/26.
 */
public interface FindStudentBookOrderForWXByStudentCodeDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Query(nativeQuery = true, value = "select * from (" +
            "select * from (" +
            "SELECT s.year, s.quarter, sbo.order_code, sbo.state, sum(sbotm.count) count, sum(sbotm.count*sbotm.price) money, sbop.logistic_code, sbo.create_time, 0, sbo.id FROM " +
            "semester s INNER JOIN student_book_order sbo on s.id = sbo.semester_id " +
            "INNER JOIN student_book_order_tm sbotm on sbo.order_code = sbotm.order_code " +
            "LEFT JOIN student_book_order_package sbop on sbo.package_id = sbop.id " +
            "where sbo.state > 0 and sbo.student_code = ?1 " +
            "group by s.year, s.quarter, sbo.order_code, sbo.state " +
            "order by sbo.create_time desc " +
            ") t where t.count > 0 and t.money > 0 " +
            "union all " +
            "select * from (" +
            "SELECT s.year, s.quarter, sbo.order_code, sbo.state, sum(sbotm.count) count, sum(sbotm.count*sbotm.price) money, sbop.logistic_code, sbo.create_time, 1, sbo.id FROM " +
            "semester s INNER JOIN student_book_once_order sbo on s.id = sbo.semester_id " +
            "INNER JOIN student_book_once_order_tm sbotm on sbo.id = sbotm.order_id " +
            "LEFT JOIN student_book_order_package sbop on sbo.package_id = sbop.id " +
            "where sbo.state > 0 and sbo.student_code = ?1 " +
            "group by s.year, s.quarter, sbo.order_code, sbo.state " +
            "order by sbo.create_time desc ) t where t.count > 0 and t.money > 0" +
            ") t order by t.create_time desc")
    public List<Object[]> find(String studentCode);

    @Query(nativeQuery = true, value = "select * from (" +
        "SELECT s.year, s.quarter, sbo.order_code, sbo.state, sum(sbotm.count) count, sum(sbotm.count*sbotm.price) money, sbop.logistic_code, sbo.create_time, 0 FROM " +
        "semester s INNER JOIN student_book_order sbo on s.id = sbo.semester_id " +
        "INNER JOIN student_book_order_tm sbotm on sbo.order_code = sbotm.order_code " +
        "LEFT JOIN student_book_order_package sbop on sbo.package_id = sbop.id " +
        "where sbo.state = 0 and sbo.student_code = ?1 " +
        "group by s.year, s.quarter, sbo.order_code, sbo.state " +
        "order by sbo.create_time desc " +
        ") t where t.count > 0 and t.money > 0 ")
    public List<Object[]> findNotConfrim(String studentCode);
}
