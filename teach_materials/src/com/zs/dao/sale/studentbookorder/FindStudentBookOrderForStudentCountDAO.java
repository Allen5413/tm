package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;


public interface FindStudentBookOrderForStudentCountDAO extends EntityJpaDao<StudentBookOrder, Long> {
    /**
     * 统计学生发书单的订单总数量
     * Created by Allen on 2015/7/18.
     */
    @Query(nativeQuery = true, value = "SELECT " +
            "count(t.order_code) " +
            "FROM " +
            "(SELECT " +
            "sbo.order_code " +
            "FROM " +
            "student_book_order sbo, " +
            "student_book_order_tm sbotm, " +
            "teach_material tm " +
            "WHERE " +
            "sbo.order_code = sbotm.order_code " +
            "AND sbotm.teach_material_id = tm.id " +
            "AND tm.state = 0 " +
            "AND sbotm.count > 0 " +
            "and sbo.is_stock = 0 " +
            "and sbo.semester_id = ?1 " +
            "and sbo.state = ?2 " +
            "and sbo.is_send_student = 1 " +
            "GROUP BY " +
            "sbo.order_code" +
            ") t")
    public BigInteger findOrderCount(long semesterId, int state);

        /**
         * 统计学生发书单的订单总数量
         * Created by Allen on 2015/7/18.
         */
        @Query(nativeQuery = true, value = "SELECT " +
                "count(t.order_code) " +
                "FROM " +
                "(SELECT " +
                "sbo.order_code " +
                "FROM " +
                "student_book_order sbo, " +
                "student_book_order_tm sbotm, " +
                "teach_material tm, " +
                "sync_student s " +
                "WHERE " +
                "sbo.student_code = s.code " +
                "AND sbo.order_code = sbotm.order_code " +
                "AND sbotm.teach_material_id = tm.id " +
                "AND tm.state = 0 " +
                "AND sbotm.count > 0 " +
                "and sbo.is_stock = 0 " +
                "and sbo.semester_id = ?1 " +
                "and sbo.state = ?2 " +
                "and sbo.is_send_student = 1 " +
                "and s.spot_code = ?3 " +
                "GROUP BY " +
                "sbo.order_code" +
                ") t")
        public BigInteger findOrderCount(long semesterId, int state, String spotCode);


    /**
     * 统计学生发书单，这里只能统计一条数据，如果一次打印订单数不够总订单数，就用分页的方法多查询几次
     * Created by Allen on 2015/7/18.
     */
    @Query(nativeQuery = true, value = "SELECT " +
            "count(t.order_code), " +
            "sum(t.tmCount), " +
            "sum(t.totalPrice) " +
            "FROM (" +
            "SELECT " +
            "sbo.order_code, " +
            "sum(sbotm.count) tmCount, " +
            "sum(sbotm.count * sbotm.price) totalPrice " +
            "FROM " +
            "(select * from student_book_order where is_stock = 0 and semester_id = ?1 and state = ?2 and is_send_student = 1 LIMIT ?3, ?4) sbo, " +
            "student_book_order_tm sbotm, " +
            "teach_material tm WHERE " +
            "sbo.order_code = sbotm.order_code " +
            "AND sbotm.teach_material_id = tm.id " +
            "AND tm.state = 0 " +
            "AND sbotm.count > 0 " +
            "GROUP BY sbo.order_code) t " +
            "WHERE " +
            "t.tmCount > 0")
    public Object[] find(long semesterId, int state, int pageNum, int countNum);

        /**
         * 统计学生发书单，这里只能统计一条数据，如果一次打印订单数不够总订单数，就用分页的方法多查询几次
         * Created by Allen on 2015/7/18.
         */
        @Query(nativeQuery = true, value = "SELECT " +
                "count(t.order_code), " +
                "sum(t.tmCount), " +
                "sum(t.totalPrice) " +
                "FROM (" +
                "SELECT " +
                "sbo.order_code, " +
                "sum(sbotm.count) tmCount, " +
                "sum(sbotm.count * sbotm.price) totalPrice " +
                "FROM " +
                "(select sb.* from student_book_order sb, sync_student s where sb.student_code = s.code and sb.is_stock = 0 and sb.semester_id = ?1 and sb.state = ?2 and sb.is_send_student = 1 and s.spot_code = ?5 LIMIT ?3, ?4) sbo, " +
                "student_book_order_tm sbotm, " +
                "teach_material tm WHERE " +
                "sbo.order_code = sbotm.order_code " +
                "AND sbotm.teach_material_id = tm.id " +
                "AND tm.state = 0 " +
                "AND sbotm.count > 0 " +
                "GROUP BY sbo.order_code) t " +
                "WHERE " +
                "t.tmCount > 0")
        public Object[] find(long semesterId, int state, int pageNum, int countNum, String spotCode);

    /**
     * 统计学生发书单，已打印的查询
     * Created by Allen on 2015/7/18.
     */
    @Query(nativeQuery = true, value = "SELECT " +
            "count(t.order_code), " +
            "sum(t.tmCount), " +
            "sum(t.totalPrice), " +
            "t.operate_time, " +
            "t.operator "+
            "FROM (" +
            "SELECT " +
            "sbo.order_code, " +
            "sum(sbotm.count) tmCount, " +
            "sum(sbotm.count * sbotm.price) totalPrice, " +
            "sbo.operate_time, " +
            "sbo.operator "+
            "FROM " +
            "(select * from student_book_order where is_stock = 0 and semester_id = ?1 and state = ?2 and is_send_student = 1) sbo, " +
            "student_book_order_tm sbotm, " +
            "teach_material tm WHERE " +
            "sbo.order_code = sbotm.order_code " +
            "AND sbotm.teach_material_id = tm.id " +
            "AND tm.state = 0 " +
            "AND sbotm.count > 0 " +
            "GROUP BY sbo.order_code, " +
            "sbo.operate_time, " +
            "sbo.operator) t " +
            "WHERE " +
            "t.tmCount > 0 " +
            "GROUP BY " +
            "t.operate_time, " +
            "t.operator")
    public List<Object[]> findPrint(long semesterId, int state);

        /**
         * 统计学生发书单，已打印的查询
         * Created by Allen on 2015/7/18.
         */
        @Query(nativeQuery = true, value = "SELECT " +
                "count(t.order_code), " +
                "sum(t.tmCount), " +
                "sum(t.totalPrice), " +
                "t.operate_time, " +
                "t.operator "+
                "FROM (" +
                "SELECT " +
                "sbo.order_code, " +
                "sum(sbotm.count) tmCount, " +
                "sum(sbotm.count * sbotm.price) totalPrice, " +
                "sbo.operate_time, " +
                "sbo.operator "+
                "FROM " +
                "(select sb.* from student_book_order sb, sync_student s where sb.student_code = s.code and sb.is_stock = 0 and sb.semester_id = ?1 and sb.state = ?2 and sb.is_send_student = 1 and s.spot_code = ?3) sbo, " +
                "student_book_order_tm sbotm, " +
                "teach_material tm WHERE " +
                "sbo.order_code = sbotm.order_code " +
                "AND sbotm.teach_material_id = tm.id " +
                "AND tm.state = 0 " +
                "AND sbotm.count > 0 " +
                "GROUP BY sbo.order_code, " +
                "sbo.operate_time, " +
                "sbo.operator) t " +
                "WHERE " +
                "t.tmCount > 0 " +
                "GROUP BY " +
                "t.operate_time, " +
                "t.operator")
        public List<Object[]> findPrint(long semesterId, int state, String spotCode);
}
