package com.zs.dao.statis;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 统计历史学期的订单发出情况
 * Created by Allen on 2015/9/25.
 */
public interface FindHistoryOrderSendDAO extends EntityJpaDao<StudentBookOrder, Long> {

        /**
         * 统计历史订单发出情况
          * @return
         * @throws Exception
         */
        @Query(nativeQuery = true, value = "select t.id, t.year, CASE WHEN t.quarter = 0 THEN '上学期' ELSE '下学期' END, t.orderCount, t2.packageCount, t3.price from (" +
                "select countOrder_t.id, countOrder_t.year, countOrder_t.quarter, sum(countOrder_t.orderCount) orderCount from (" +
                "select s.id, s.year, s.quarter, count(*) orderCount from student_book_order sbo, semester s " +
                "where sbo.state > 3 and sbo.semester_id = s.id " +
                "GROUP BY s.year, s.quarter " +
                "UNION ALL " +
                "select s.id, s.year, s.quarter, count(*) orderCount from teach_material_place_order tmpo, semester s " +
                "where tmpo.order_status > '3' and tmpo.semester_id = s.id " +
                "GROUP BY s.id, s.year, s.quarter " +
                "UNION ALL " +
                "SELECT s.id, s. YEAR, s. QUARTER, count(*) orderCount from student_book_once_order sbo, semester s " +
                "WHERE sbo.state > 4 AND sbo.semester_id = s.id " +
                "GROUP BY s. YEAR, s. QUARTER) countOrder_t " +
                "GROUP BY id, year, quarter) t " +
                "LEFT JOIN " +
                "(select countPackage_t.id, countPackage_t.year, countPackage_t.quarter, sum(countPackage_t.packageCount) packageCount from(" +
                "select countStudentPackage_t.id, countStudentPackage_t.year, countStudentPackage_t.quarter, count(countStudentPackage_t.pid) packageCount from (" +
                "select DISTINCT s.id, s.year, s.quarter, sbop.id pid from student_book_order sbo, semester s, student_book_order_package sbop " +
                "where sbo.state > 3 and sbo.semester_id = s.id and sbo.package_id = sbop.id and sbop.send_time is not null" +
                ") countStudentPackage_t " +
                "GROUP BY id, year, quarter " +
                "UNION ALL " +
                "select countPlacePackage_t.id, countPlacePackage_t.year, countPlacePackage_t.quarter, count(countPlacePackage_t.pid) packageCount from (" +
                "select DISTINCT s.id, s.year, s.quarter, pop.id pid from teach_material_place_order tmpo, semester s, place_order_package pop " +
                "where tmpo.order_status > '3' and tmpo.semester_id = s.id and tmpo.package_id = pop.id and pop.send_time is not null" +
                ") countPlacePackage_t " +
                "GROUP BY id, year, quarter " +
                "UNION ALL " +
                "SELECT countStudentPackage_t.id, countStudentPackage_t. YEAR, countStudentPackage_t. QUARTER, count(countStudentPackage_t.pid) packageCount FROM ( " +
                "SELECT DISTINCT s.id, s. YEAR, s. QUARTER, sbop.id pid FROM student_book_once_order sbo, semester s, student_book_order_package sbop " +
                "WHERE sbo.state > 4 AND sbo.semester_id = s.id AND sbo.package_id = sbop.id AND sbop.send_time IS NOT NULL " +
                ") countStudentPackage_t " +
                "GROUP BY id, YEAR, QUARTER) countPackage_t " +
                "GROUP BY id, year, quarter) t2 on t.year = t2.year and t.quarter = t2.quarter " +
                "LEFT JOIN " +
                "(select countPrice_t.id, countPrice_t.year, countPrice_t.quarter, sum(countPrice_t.price) price from(" +
                "select s.id, s.year, s.quarter, sum(sbotm.count * sbotm.price) price from student_book_order sbo, student_book_order_tm sbotm, semester s " +
                "where sbo.state > 3 and sbo.semester_id = s.id and sbo.order_code = sbotm.order_code and sbotm.count > 0 and sbotm.is_send = 1 " +
                "GROUP BY id, year, quarter " +
                "UNION ALL " +
                "select s.id, s.year, s.quarter, sum(potm.count * potm.tm_price) price from teach_material_place_order tmpo, place_order_teach_material potm, semester s " +
                "where tmpo.order_status > '3' and tmpo.semester_id = s.id and tmpo.id = potm.order_id and potm.count > 0 and potm.is_send = 1 " +
                "GROUP BY id, year, quarter " +
                "UNION ALL " +
                "SELECT s.id, s.YEAR, s.QUARTER, sum(sbotm.count * sbotm.price) price FROM student_book_once_order sbo, student_book_once_order_tm sbotm, semester s " +
                "WHERE sbo.state > 4 AND sbo.semester_id = s.id AND sbo.id = sbotm.order_id AND sbotm.count > 0 AND sbotm.is_send = 1 " +
                "GROUP BY id, YEAR, QUARTER" +
                ") countPrice_t " +
                "GROUP BY id, year, quarter) t3 on t.year = t3.year and t.quarter = t3.quarter " +
                "order by t.id, t.year, t.quarter")
        public List<Object[]> findHistoryOrderSend()throws Exception;

        /**
         * 统计历史订单发出情况
         * @return
         * @throws Exception
         */
        @Query(nativeQuery = true, value = "select t.year, case when t.quarter = 0 then '上学期' else '下学期' end, t.code, t.name, ifnull(t.orderCount, 0), ifnull(t2.packageCount, 0), ifnull(t3.price, 0) from (" +
                "select countOrder_t.year, countOrder_t.quarter, countOrder_t.code, countOrder_t.name, sum(countOrder_t.orderCount) orderCount from (" +
                "select se.year, se.quarter, sp.code, sp.name, count(sbo.id) orderCount from student_book_order sbo, sync_student s, sync_spot sp, semester se " +
                "where sbo.state > 3 and sbo.semester_id = se.id and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY se.year, se.quarter, sp.code, sp.name " +
                "UNION ALL " +
                "select se.year, se.quarter, sp.code, sp.name, count(*) orderCount from teach_material_place_order tmpo, sync_spot sp, semester se " +
                "where tmpo.order_status > '3' and tmpo.semester_id = se.id and tmpo.spot_code = sp.code " +
                "GROUP BY se.year, se.quarter, sp.code, sp.name " +
                "UNION ALL " +
                "select se.year, se.quarter, sp.code, sp.name, count(sbo.id) orderCount from student_book_once_order sbo, sync_student s, sync_spot sp, semester se " +
                "where sbo.state > 4 and sbo.semester_id = se.id and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY se.year, se.quarter, sp.code, sp.name " +
                ") countOrder_t " +
                "GROUP BY year, quarter, code, name) t " +
                "LEFT JOIN " +
                "(select countPackage_t.year, countPackage_t.quarter, countPackage_t.code, countPackage_t.name, sum(countPackage_t.packageCount) packageCount from(" +
                "select countStudentPackage_t.year, countStudentPackage_t.quarter, countStudentPackage_t.code, countStudentPackage_t.name, count(countStudentPackage_t.id) packageCount from (" +
                "select DISTINCT se.year, se.quarter, sp.code, sp.name, sbop.id from student_book_order sbo, student_book_order_package sbop, sync_student s, sync_spot sp, semester se " +
                "where sbo.state > 3 and sbo.semester_id = se.id and sbo.package_id = sbop.id and sbop.send_time is not null and sbo.student_code = s.code and s.spot_code = sp.code " +
                ") countStudentPackage_t " +
                "GROUP BY year, quarter, code, name " +
                "UNION ALL " +
                "select countPlacePackage_t.year, countPlacePackage_t.quarter, countPlacePackage_t.code, countPlacePackage_t.name, count(countPlacePackage_t.id) packageCount from (" +
                "select DISTINCT se.year, se.quarter, sp.code, sp.name, pop.id from teach_material_place_order tmpo, sync_spot sp, place_order_package pop, semester se " +
                "where tmpo.order_status > '3' and tmpo.semester_id = se.id and tmpo.package_id = pop.id and pop.send_time is not null and tmpo.spot_code = sp.code" +
                ") countPlacePackage_t " +
                "GROUP BY year, quarter, code, name " +
                "UNION ALL " +
                "select countStudentPackage_t.year, countStudentPackage_t.quarter, countStudentPackage_t.code, countStudentPackage_t.name, count(countStudentPackage_t.id) packageCount from (" +
                "select DISTINCT se.year, se.quarter, sp.code, sp.name, sbop.id from student_book_once_order sbo, student_book_order_package sbop, sync_student s, sync_spot sp, semester se " +
                "where sbo.state > 4 and sbo.semester_id = se.id and sbo.package_id = sbop.id and sbop.send_time is not null and sbo.student_code = s.code and s.spot_code = sp.code " +
                ") countStudentPackage_t " +
                "GROUP BY year, quarter, code, name " +
                ") countPackage_t " +
                "GROUP BY year, quarter, code, name) t2 on t.year = t2.year and t.quarter = t2.quarter and t.code = t2.code and t.name = t2.name " +
                "LEFT JOIN " +
                "(select countPrice_t.year, countPrice_t.quarter, countPrice_t.code, countPrice_t.name, sum(countPrice_t.price) price from(" +
                "select se.year, se.quarter, sp.code, sp.name, sum(sbotm.count * sbotm.price) price from student_book_order sbo, student_book_order_tm sbotm, sync_student s, sync_spot sp, semester se " +
                "where sbo.state > 3 and sbo.semester_id = se.id and sbo.order_code = sbotm.order_code and sbotm.count > 0 and sbotm.is_send = 1 and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY year, quarter, code, name " +
                "UNION ALL " +
                "select se.year, se.quarter, sp.code, sp.name, sum(potm.count * potm.tm_price) price from teach_material_place_order tmpo, place_order_teach_material potm, sync_spot sp, semester se " +
                "where tmpo.order_status > '3' and tmpo.semester_id = se.id and tmpo.id = potm.order_id and potm.count > 0 and potm.is_send = 1 and tmpo.spot_code = sp.code " +
                "GROUP BY year, quarter, code, name " +
                "UNION ALL " +
                "select se.year, se.quarter, sp.code, sp.name, sum(sbotm.count * sbotm.price) price from student_book_once_order sbo, student_book_once_order_tm sbotm, sync_student s, sync_spot sp, semester se " +
                "where sbo.state > 4 and sbo.semester_id = se.id and sbo.id = sbotm.order_id and sbotm.count > 0 and sbotm.is_send = 1 and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY year, quarter, code, name " +
                ") countPrice_t " +
                "GROUP BY year, quarter, code, name) t3 on t.year = t3.year and t.quarter = t3.quarter and t.code = t3.code and t.name = t3.name " +
                "order by t.year, t.quarter, t.code")
        public List<Object[]> findHistoryOrderSendForSpot()throws Exception;

        /**
         * 统计某个学期的学习中心订单发出情况
         * @param semesterId
         * @return
         * @throws Exception
         */
        @Query(nativeQuery = true, value = "select t.code, t.name, ifnull(t.orderCount, 0), ifnull(t2.packageCount, 0), ifnull(t3.price, 0) from (" +
                "select countOrder_t.code, countOrder_t.name, sum(countOrder_t.orderCount) orderCount from (" +
                "select sp.code, sp.name, count(sbo.id) orderCount from student_book_order sbo, sync_student s, sync_spot sp " +
                "where sbo.state > 3 and sbo.semester_id = ?1 and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY sp.code, sp.name " +
                "UNION ALL " +
                "select sp.code, sp.name, count(*) orderCount from teach_material_place_order tmpo, sync_spot sp " +
                "where tmpo.order_status > '3' and tmpo.semester_id = ?1 and tmpo.spot_code = sp.code " +
                "GROUP BY sp.code, sp.name " +
                "UNION ALL " +
                "select sp.code, sp.name, count(sbo.id) orderCount from student_book_once_order sbo, sync_student s, sync_spot sp " +
                "where sbo.state > 4 and sbo.semester_id = ?1 and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY sp.code, sp.name " +
                ") countOrder_t " +
                "GROUP BY code, name) t " +
                "LEFT JOIN " +
                "(select countPackage_t.code, countPackage_t.name, sum(countPackage_t.packageCount) packageCount from(" +
                "select countStudentPackage_t.code, countStudentPackage_t.name, count(countStudentPackage_t.id) packageCount from (" +
                "select DISTINCT sp.code, sp.name, sbop.id from student_book_order sbo, student_book_order_package sbop, sync_student s, sync_spot sp " +
                "where sbo.state > 3 and sbo.semester_id = ?1 and sbo.package_id = sbop.id and sbop.send_time is not null and sbo.student_code = s.code and s.spot_code = sp.code " +
                ") countStudentPackage_t " +
                "GROUP BY code, name " +
                "UNION ALL " +
                "select countPlacePackage_t.code, countPlacePackage_t.name, count(countPlacePackage_t.id) packageCount from (" +
                "select DISTINCT sp.code, sp.name, pop.id from teach_material_place_order tmpo, sync_spot sp, place_order_package pop " +
                "where tmpo.order_status > '3' and tmpo.semester_id = ?1 and tmpo.package_id = pop.id and pop.send_time is not null and tmpo.spot_code = sp.code" +
                ") countPlacePackage_t " +
                "GROUP BY code, name " +
                "UNION ALL " +
                "select countStudentPackage_t.code, countStudentPackage_t.name, count(countStudentPackage_t.id) packageCount from (" +
                "select DISTINCT sp.code, sp.name, sbop.id from student_book_once_order sbo, student_book_order_package sbop, sync_student s, sync_spot sp " +
                "where sbo.state > 4 and sbo.semester_id = ?1 and sbo.package_id = sbop.id and sbop.send_time is not null and sbo.student_code = s.code and s.spot_code = sp.code " +
                ") countStudentPackage_t " +
                "GROUP BY code, name " +
                ") countPackage_t " +
                "GROUP BY code, name) t2 on t.code = t2.code and t.name = t2.name " +
                "LEFT JOIN " +
                "(select countPrice_t.code, countPrice_t.name, sum(countPrice_t.price) price from(" +
                "select sp.code, sp.name, sum(sbotm.count * sbotm.price) price from student_book_order sbo, student_book_order_tm sbotm, sync_student s, sync_spot sp " +
                "where sbo.state > 3 and sbo.semester_id = ?1 and sbo.order_code = sbotm.order_code and sbotm.count > 0 and sbotm.is_send = 1 and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY code, name " +
                "UNION ALL " +
                "select sp.code, sp.name, sum(potm.count * potm.tm_price) price from teach_material_place_order tmpo, place_order_teach_material potm, sync_spot sp " +
                "where tmpo.order_status > '3' and tmpo.semester_id = ?1 and tmpo.id = potm.order_id and potm.count > 0 and potm.is_send = 1 and tmpo.spot_code = sp.code " +
                "GROUP BY code, name " +
                "UNION ALL " +
                "select sp.code, sp.name, sum(sbotm.count * sbotm.price) price from student_book_once_order sbo, student_book_once_order_tm sbotm, sync_student s, sync_spot sp " +
                "where sbo.state > 4 and sbo.semester_id = ?1 and sbo.id = sbotm.order_id and sbotm.count > 0 and sbotm.is_send = 1 and sbo.student_code = s.code and s.spot_code = sp.code " +
                "GROUP BY code, name " +
                ") countPrice_t " +
                "GROUP BY code, name) t3 on t.code = t3.code and t.name = t3.name " +
                "order by t.code")
        public List<Object[]> findSpotOrderSendBySemesterId(long semesterId)throws Exception;
}
