package com.zs.dao.statis;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;


public interface FindOrderCountBySemesterIdDAO extends EntityJpaDao<StudentBookOrder, Long> {

    /**
     * 查询一个学期的学生订单数量
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query("SELECT COUNT(*) FROM StudentBookOrder WHERE semesterId = ?1")
    public Long findStudentOrderCountBySemesterId(long semesterId)throws Exception;

    /**
     * 查询一个学期的预订单数量
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from (SELECT DISTINCT tmpo.id FROM teach_material_place_order tmpo, place_order_teach_material potm\n" +
            "WHERE tmpo.semester_id = ?1 AND tmpo.id = potm.order_id AND potm.count > 0) t")
    public BigInteger findPlaceOrderCountBySemesterId(long semesterId)throws Exception;


    /**
     * 查询已经确认了的一个学期的学生订单数量
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from (SELECT DISTINCT sbo.id FROM student_book_order sbo, student_book_order_tm sbotm " +
            "WHERE sbo.semester_id = ?1 AND sbo.state > 0 AND sbo.order_code = sbotm.order_code AND sbotm.count > 0) t")
    public BigInteger findConfirmStudentOrderCountBySemesterId(long semesterId)throws Exception;

    /**
     * 查询已经确认了的一个学期的一次性订单数量
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from (SELECT DISTINCT sbo.id FROM student_book_once_order sbo, student_book_once_order_tm sbotm " +
            "WHERE sbo.semester_id = ?1 AND sbo.state > 0 AND sbo.id = sbotm.order_id AND sbotm.count > 0) t")
    public BigInteger findConfirmOnceCountBySemesterId(long semesterId)throws Exception;

    /**
     * 查询一个学期学生订单的还未发出的大包数量
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(1) from student_book_order sbo, student_book_order_package sbop " +
            "where sbop.id = sbo.package_id and sbo.semester_id = ?1 and sbop.send_time is null")
    public BigInteger findNotSendStudentOrderPackageCountBySemesterId(long semesterId)throws Exception;

    /**
     * 查询一个学期一次性订单的还未发出的大包数量
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(1) from student_book_once_order sbo, student_book_order_package sbop " +
            "where sbop.id = sbo.package_id and sbo.semester_id = ?1 and sbop.send_time is null")
    public BigInteger findNotSendOnceOrderPackageCountBySemesterId(long semesterId)throws Exception;

    /**
     * 查询一个学期预订单的还未发出的大包数量
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(1) from teach_material_place_order tmpo, place_order_package pop " +
            "where pop.id = tmpo.package_id and tmpo.semester_id = ?1 and pop.send_time is null")
    public BigInteger findNotSendPlaceOrderPackageCountBySemesterId(long semesterId)throws Exception;

    /**
     * 查询一个学期未发出的学生订单的总金额
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select sum(sbotm.count * sbotm.price) from student_book_order sbo, student_book_order_tm sbotm, teach_material tm " +
            "where sbo.order_code = sbotm.order_code and sbotm.teach_material_id = tm.id and tm.state = 0 and sbo.state BETWEEN 1 and 3 and sbo.semester_id = ?1")
    public Double findNotSendStudentOrderPriceBySemesterId(long semesterId)throws Exception;

    /**
     * 查询一个学期未发出的一次性订单的总金额
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select sum(sbotm.count * sbotm.price) from student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm " +
            "where sbo.id = sbotm.order_id and sbotm.teach_material_id = tm.id and tm.state = 0 and sbo.state BETWEEN 1 and 4 and sbo.semester_id = ?1")
    public Double findNotSendOnceOrderPriceBySemesterId(long semesterId)throws Exception;


    /**
     * 查询一个学期未发出的预订单的总金额
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select sum(t.price) from (" +
            "select sum(potm.count * tm.price) price  from teach_material_place_order tmpo, place_order_teach_material potm, teach_material_course tmc, teach_material tm " +
            "where tmpo.id = potm.order_id and tmpo.order_status BETWEEN '1' and '3' and tmpo.semester_id = ?1 and potm.course_code = tmc.course_code " +
            "and tmc.teach_material_id = tm.id and tm.state = 0 " +
            "UNION ALL " +
            "select sum(potm.count * tm.price) price from teach_material_place_order tmpo, place_order_teach_material potm, " +
            "set_teach_material stm, set_teach_material_tm stmtm, teach_material tm " +
            "where tmpo.id = potm.order_id and tmpo.order_status BETWEEN '1' and '3' and tmpo.semester_id = ?1 and potm.course_code = stm.buy_course_code " +
            "and stm.id = stmtm.set_teach_material_id and stmtm.teach_material_id = tm.id and tm.state = 0 " +
            "UNION ALL " +
            "select sum(potm.count * potm.tm_price) price from teach_material_place_order tmpo, place_order_teach_material potm, teach_material tm " +
            "where tmpo.id = potm.order_id and potm.teach_material_id = tm.id and tm.state = 0 and tmpo.order_status BETWEEN '1' and '3' and tmpo.semester_id = ?1) t")
    public Double findNotSendPlaceOrderPriceBySemesterId(long semesterId)throws Exception;
}
