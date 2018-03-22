package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/10/24.
 */
public interface FindStudentBookOrderTMForStudentPrintDAO extends EntityJpaDao<StudentBookOrder, Long> {

        /**
         * 查询未打印之前的方法
         * @param semesterId
         * @param state
         * @param pageNum
         * @param countNum
         * @return
         */
    @Query(nativeQuery = true, value = "SELECT " +
            "s.spec_code, " +
            "s.level_code, " +
            "s.CODE, " +
            "s.NAME, " +
            "sbo.order_code, " +
            "sbo.state, " +
            "sbotm.course_code, " +
            "tm. NAME AS tmName, " +
            "tm.author, " +
            "sbotm.price, " +
            "sbotm.count, " +
            "sbo.is_send_student, " +
            "sbo.send_address, " +
            "sbo.send_phone, " +
            "sbo.send_zip_code, " +
            "sp.code spotCode, "+
            "sp.name spotName "+
            "FROM " +
            "sync_student s, " +
            "sync_spot sp, "+
            "(select * from student_book_order where is_stock = 0 and semester_id = ?1 and state = ?2 and is_send_student = 1 LIMIT ?3, ?4) sbo, " +
            "student_book_order_tm sbotm, " +
            "teach_material tm " +
            "WHERE " +
            "s. CODE = sbo.student_code " +
            "AND s.spot_code = sp.code "+
            "AND sbo.order_code = sbotm.order_code " +
            "AND sbotm.teach_material_id = tm.id " +
            "AND tm.state = 0 " +
            "AND sbo.is_stock = 0 " +
            "AND sbotm.count > 0 " +
            "ORDER BY " +
            "s.CODE, " +
            "sbotm.order_code, " +
            "sbotm.teach_material_id")
    public List<Object[]> find(long semesterId, int state, int pageNum, int countNum);

        /**
         * 查询未打印之前的方法
         * @param semesterId
         * @param state
         * @param pageNum
         * @param countNum
         * @return
         */
        @Query(nativeQuery = true, value = "SELECT " +
                "s.spec_code, " +
                "s.level_code, " +
                "s.CODE, " +
                "s.NAME, " +
                "sbo.order_code, " +
                "sbo.state, " +
                "sbotm.course_code, " +
                "tm. NAME AS tmName, " +
                "tm.author, " +
                "sbotm.price, " +
                "sbotm.count, " +
                "sbo.is_send_student, " +
                "sbo.send_address, " +
                "sbo.send_phone, " +
                "sbo.send_zip_code, " +
                "sp.code spotCode, "+
                "sp.name spotName "+
                "FROM " +
                "sync_student s, " +
                "sync_spot sp, "+
                "(select * from student_book_order where is_stock = 0 and semester_id = ?1 and state = ?2 and is_send_student = 1 LIMIT ?3, ?4) sbo, " +
                "student_book_order_tm sbotm, " +
                "teach_material tm " +
                "WHERE " +
                "s. CODE = sbo.student_code " +
                "AND s.spot_code = sp.code "+
                "AND sbo.order_code = sbotm.order_code " +
                "AND sbotm.teach_material_id = tm.id " +
                "AND tm.state = 0 " +
                "AND sbo.is_stock = 0 " +
                "AND sbotm.count > 0 " +
                "AND sp.code = ?5 " +
                "ORDER BY " +
                "s.CODE, " +
                "sbotm.order_code, " +
                "sbotm.teach_material_id")
        public List<Object[]> find(long semesterId, int state, int pageNum, int countNum, String spotCode);


        /**
         * 查询已打印的
         * @param semesterId
         * @param state
         * @param operateTime
         * @return
         */
        @Query(nativeQuery = true, value = "SELECT " +
                "s.spec_code, " +
                "s.level_code, " +
                "s.CODE, " +
                "s.NAME, " +
                "sbo.order_code, " +
                "sbo.state, " +
                "sbotm.course_code, " +
                "tm. NAME AS tmName, " +
                "tm.author, " +
                "sbotm.price, " +
                "sbotm.count, " +
                "sbo.is_send_student, " +
                "sbo.send_address, " +
                "sbo.send_phone, " +
                "sbo.send_zip_code, " +
                "sp.code spotCode, "+
                "sp.name spotName "+
                "FROM " +
                "sync_student s, " +
                "sync_spot sp, "+
                "(select * from student_book_order where is_stock = 0 and semester_id = ?1 and state = ?2 and is_send_student = 1 AND operate_time = ?3) sbo, " +
                "student_book_order_tm sbotm, " +
                "teach_material tm " +
                "WHERE " +
                "s. CODE = sbo.student_code " +
                "AND s.spot_code = sp.code "+
                "AND sbo.order_code = sbotm.order_code " +
                "AND sbotm.teach_material_id = tm.id " +
                "AND tm.state = 0 " +
                "AND sbo.is_stock = 0 " +
                "AND sbotm.count > 0 " +
                "ORDER BY " +
                "s.CODE, " +
                "sbotm.order_code, " +
                "sbotm.teach_material_id")
        public List<Object[]> findPrint(long semesterId, int state, String operateTime);

        /**
         * 查询已打印的
         * @param semesterId
         * @param state
         * @param operateTime
         * @return
         */
        @Query(nativeQuery = true, value = "SELECT " +
                "s.spec_code, " +
                "s.level_code, " +
                "s.CODE, " +
                "s.NAME, " +
                "sbo.order_code, " +
                "sbo.state, " +
                "sbotm.course_code, " +
                "tm. NAME AS tmName, " +
                "tm.author, " +
                "sbotm.price, " +
                "sbotm.count, " +
                "sbo.is_send_student, " +
                "sbo.send_address, " +
                "sbo.send_phone, " +
                "sbo.send_zip_code, " +
                "sp.code spotCode, "+
                "sp.name spotName "+
                "FROM " +
                "sync_student s, " +
                "sync_spot sp, "+
                "(select * from student_book_order where is_stock = 0 and semester_id = ?1 and state = ?2 and is_send_student = 1 AND operate_time = ?3) sbo, " +
                "student_book_order_tm sbotm, " +
                "teach_material tm " +
                "WHERE " +
                "s. CODE = sbo.student_code " +
                "AND s.spot_code = sp.code "+
                "AND sbo.order_code = sbotm.order_code " +
                "AND sbotm.teach_material_id = tm.id " +
                "AND tm.state = 0 " +
                "AND sbo.is_stock = 0 " +
                "AND sbotm.count > 0 " +
                "AND sp.code = ?4 " +
                "ORDER BY " +
                "s.CODE, " +
                "sbotm.order_code, " +
                "sbotm.teach_material_id")
        public List<Object[]> findPrint(long semesterId, int state, String operateTime, String spotCode);
}
