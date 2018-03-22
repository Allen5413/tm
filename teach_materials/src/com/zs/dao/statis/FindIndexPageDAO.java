package com.zs.dao.statis;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.User;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

/**
 * 首页的一些提示性的查询
 * Created by Allen on 2016/11/1.
 */
public interface FindIndexPageDAO extends EntityJpaDao<User, Long> {

    /**
     * 查询还没有审核的交费记录条数
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT count(*) FROM spot_pay_pol_temp t1, sync_spot t2 WHERE t1.spot_code = t2. CODE AND t1.is_sure = 0")
    public BigInteger findWaitAuditPayCount();

    /**
     * 统计学生订单还没有打印的记录条数
     * @param state
     * @param semesterId
     * @return
     */
    @Query(nativeQuery = true, value = "select count(*) FROM " +
            "(" +
            "SELECT sbo.order_code " +
            "FROM student_book_order sbo, student_book_order_tm sbotm, teach_material tm " +
            "WHERE sbo.order_code = sbotm.order_code AND sbotm.teach_material_id = tm.id AND tm.state = 0 AND sbo.is_stock = 0 " +
            "and sbo.state = ?1 and sbo.semester_id = ?2 and sbo.is_send_student = ?3 " +
            "group by sbo.order_code having(sum(sbotm.count) > 0)" +
            ") t")
    public BigInteger findNotPrintOrderCount(int state, long semesterId, int isSendStudent);

    /**
     * 统计学生一次性订单还没有打印的记录条数
     * @param state
     * @param semesterId
     * @return
     */
    @Query(nativeQuery = true, value = "select count(*) FROM " +
            "(" +
            "SELECT sbo.order_code " +
            "FROM student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm " +
            "WHERE sbo.id = sbotm.order_id AND sbotm.teach_material_id = tm.id AND tm.state = 0 " +
            "and sbo.state = ?1 and sbo.semester_id = ?2 and sbo.is_send_student = ?3 " +
            "group by sbo.order_code having(sum(sbotm.count) > 0)" +
            ") t")
    public BigInteger findNotPrintOnceOrderCount(int state, long semesterId, int isSendStudent);

    /**
     * 统计学生订单记录条数
     * @param state
     * @param semesterId
     * @return
     */
    @Query(nativeQuery = true, value = "select count(*) from " +
            "(SELECT " +
            "t.order_code " +
            "FROM " +
            "( " +
            "SELECT " +
            "tmpo.order_code, " +
            "sum(potm.count) count " +
            "FROM " +
            "teach_material_place_order tmpo, " +
            "place_order_teach_material potm, " +
            "teach_material_course tmc, " +
            "teach_material tm " +
            "WHERE " +
            "tmpo.id = potm.order_id " +
            "AND potm.course_code = tmc.course_code " +
            "AND tmc.teach_material_id = tm.id " +
            "AND tmpo.is_stock = 0 " +
            "AND potm.count > 0 " +
            "AND tm.state = 0 " +
            "AND tmpo.semester_id = ?2 " +
            "AND tmpo.order_status = ?1 " +
            "GROUP BY " +
            "tmpo.order_code " +
            "UNION ALL " +
            "SELECT " +
            "tmpo.order_code, " +
            "sum(potm.count) count " +
            "FROM " +
            "teach_material_place_order tmpo, " +
            "place_order_teach_material potm, " +
            "set_teach_material stm, " +
            "set_teach_material_tm stmtm, " +
            "teach_material tm " +
            "WHERE " +
            "tmpo.id = potm.order_id " +
            "AND potm.course_code = stm.buy_course_code " +
            "AND stm.id = stmtm.set_teach_material_id " +
            "AND stmtm.teach_material_id = tm.id " +
            "AND tmpo.is_stock = 0 " +
            "AND potm.count > 0 " +
            "AND tm.state = 0 " +
            "AND tmpo.semester_id = ?2 " +
            "AND tmpo.order_status = ?1 " +
            "GROUP BY " +
            "tmpo.order_code " +
            "UNION ALL " +
            "SELECT " +
            "tmpo.order_code, " +
            "sum(potm.count) count " +
            "FROM " +
            "teach_material_place_order tmpo, " +
            "place_order_teach_material potm, " +
            "teach_material tm " +
            "WHERE " +
            "tmpo.id = potm.order_id " +
            "AND potm.teach_material_id = tm.id " +
            "AND tmpo.is_stock = 0 " +
            "AND potm.count > 0 " +
            "AND tm.state = 0 " +
            "AND tmpo.semester_id = ?2 " +
            "AND tmpo.order_status = ?1 " +
            "GROUP BY " +
            "tmpo.order_code " +
            ") t " +
            "GROUP BY " +
            "order_code " +
            "HAVING(sum(t.count) > 0)" +
            ") tt")
    public BigInteger findNotPrintPlaceOrderCount(String state, long semesterId);


    /**
     * 统计学生订单还没有打印的记录条数
     * @param semesterId
     * @return
     */
    @Query(nativeQuery = true, value = "select count(*) from " +
        "(SELECT " +
        "t.order_code " +
        "FROM " +
        "( " +
        "SELECT " +
        "tmpo.order_code, " +
        "sum(potm.count) count " +
        "FROM " +
        "teach_material_place_order tmpo, " +
        "place_order_teach_material potm, " +
        "teach_material_course tmc, " +
        "teach_material tm " +
        "WHERE " +
        "tmpo.id = potm.order_id " +
        "AND potm.course_code = tmc.course_code " +
        "AND tmc.teach_material_id = tm.id " +
        "AND tmpo.is_stock = 0 " +
        "AND potm.count > 0 " +
        "AND tm.state = 0 " +
        "AND tmpo.semester_id = ?1 " +
        "AND (CASE WHEN tmpo.is_spec_all = 0 THEN tmpo.order_status = '1' ELSE tmpo.order_status = '2' END) " +
        "GROUP BY " +
        "tmpo.order_code " +
        "UNION ALL " +
        "SELECT " +
        "tmpo.order_code, " +
        "sum(potm.count) count " +
        "FROM " +
        "teach_material_place_order tmpo, " +
        "place_order_teach_material potm, " +
        "set_teach_material stm, " +
        "set_teach_material_tm stmtm, " +
        "teach_material tm " +
        "WHERE " +
        "tmpo.id = potm.order_id " +
        "AND potm.course_code = stm.buy_course_code " +
        "AND stm.id = stmtm.set_teach_material_id " +
        "AND stmtm.teach_material_id = tm.id " +
        "AND tmpo.is_stock = 0 " +
        "AND potm.count > 0 " +
        "AND tm.state = 0 " +
        "AND tmpo.semester_id = ?1 " +
        "AND (CASE WHEN tmpo.is_spec_all = 0 THEN tmpo.order_status = '1' ELSE tmpo.order_status = '2' END) " +
        "GROUP BY " +
        "tmpo.order_code " +
        "UNION ALL " +
        "SELECT " +
        "tmpo.order_code, " +
        "sum(potm.count) count " +
        "FROM " +
        "teach_material_place_order tmpo, " +
        "place_order_teach_material potm, " +
        "teach_material tm " +
        "WHERE " +
        "tmpo.id = potm.order_id " +
        "AND potm.teach_material_id = tm.id " +
        "AND tmpo.is_stock = 0 " +
        "AND potm.count > 0 " +
        "AND tm.state = 0 " +
        "AND tmpo.semester_id = ?1 " +
        "AND (CASE WHEN tmpo.is_spec_all = 0 THEN tmpo.order_status = '1' ELSE tmpo.order_status = '2' END) " +
        "GROUP BY " +
        "tmpo.order_code " +
        ") t " +
        "GROUP BY " +
        "order_code " +
        "HAVING(sum(t.count) > 0)" +
        ") tt")
    public BigInteger findNotPrintPlaceOrderCount(long semesterId);


}
