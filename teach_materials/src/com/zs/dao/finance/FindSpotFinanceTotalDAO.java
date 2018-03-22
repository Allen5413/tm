package com.zs.dao.finance;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.SpotExpense;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/12/7.
 */
public interface FindSpotFinanceTotalDAO extends EntityJpaDao<SpotExpense, Long> {

    /**
     * 查询学习中心教材订购情况
     * @param spotCode
     * @return
     * @throws Exception
     * */
    @Query(nativeQuery = true, value = "select id, year, quarter, case when ROUND(sum(buy), 2) < 0 then 0 else ROUND(sum(buy), 2) end, ROUND(sum(own), 2), case when max(clear_time) = '2099-12-12 00:00:00' then null else max(clear_time) end from " +
        "(" +
        "select t.id, t.year, t.quarter, sum(ifnull(t.buy, 0)) buy, sum(case when ifnull(t.pay, 0) - ifnull(t.buy, 0) > 0 then 0 else ifnull(t.buy, 0) - ifnull(t.pay, 0) end) own, IFNULL(t.clear_time, '2099-12-12 00:00:00') clear_time \n" +
        "from(" +
        "select DISTINCT sp.code, se.id seId, s.id, s.year, s.quarter, se.buy, se.pay, seo.clear_time " +
        "from semester s INNER JOIN student_expense se on s.id = se.semester_id " +
        "INNER JOIN sync_student stu on se.student_code = stu.code " +
        "INNER JOIN sync_spot sp on stu.spot_code = sp.code " +
        "INNER JOIN spot_expense_oth seo ON s.id = seo.semester_id and sp.code = seo.spot_code " +
        "where sp.code = ?1 " +
        ") t GROUP BY t.id, t.code " +
        "UNION ALL " +
        "select s.id, s.year, s.quarter, IFNULL(se.buy,0) buy, CASE WHEN IFNULL(se.buy,0)-ifnull(se.pay,0) > 0 THEN IFNULL(se.buy,0)-IFNULL(se.pay,0) ELSE 0 END own, " +
        "IFNULL(se.clear_time, '2099-12-12 00:00:00') clear_time " +
        "from spot_expense se, semester s " +
        "where se.semester_id = s.id " +
        "and se.spot_code = ?1" +
        ") t group by id, year, quarter order by id desc")
    public List<Object[]> findTMOrderInfo(String spotCode)throws Exception;

    /**
     * 查询学习中心的学生个人账户进出帐明细
     * @param spotCode
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select * from (" +
        "select * from (" +
        "select seb.create_time, s.code, s.name, seb.money, seb.detail from sync_student s, student_expense_buy seb " +
        "where s.code = seb.student_code and s.spot_code = ?1 " +
        "UNION ALL " +
        "select sep.create_time, s.code, s.name, sep.money, " +
        "CONCAT('通过', CASE WHEN sep.pay_type = 0 THEN '网银' WHEN sep.pay_type = 1 THEN '银行转账' WHEN sep.pay_type = 2 THEN '现金' ELSE '其它' END, '交费') detail " +
        "from sync_student s, student_expense_pay sep " +
        "where s.code = sep.student_code and s.spot_code = ?1 " +
        ") t ORDER BY t.create_time desc " +
        ") tt LIMIT 10")
    public List<Object[]> findStudentExpenseInfo(String spotCode)throws Exception;

    /**
     * 查询学习中心的账户进出帐明细
     * @param spotCode
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select * from (" +
       "select * from (" +
       "select seb.create_time, seb.creator, seb.money, seb.detail from spot_expense_buy seb " +
       "where seb.spot_code = ?1 " +
       "UNION ALL " +
       "select sep.create_time, sep.creator, sep.money, " +
       "CONCAT('通过', CASE WHEN sep.pay_type = 0 THEN '网银' WHEN sep.pay_type = 1 THEN '银行转账' WHEN sep.pay_type = 2 THEN '现金' ELSE '其它' END, '交费') detail " +
       "from spot_expense_pay sep " +
       "where sep.spot_code = ?1 " +
       ") t ORDER BY t.create_time desc " +
       ") tt LIMIT 10")
    public List<Object[]> findSpotExpenseInfo(String spotCode)throws Exception;

    /**
     * 查询学习中心一个学期订购的教材数量
     * @param spotCode
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select address, admin_name, name, isbn, author, price, sum(count), price*sum(count) from (" +
        "select sp.address, sp.admin_name, tm.name, tm.isbn, tm.author, sbotm.price, sbotm.count " +
        "from student_book_order sbo, student_book_order_tm sbotm, teach_material tm, sync_student s, sync_spot sp " +
        "where sbo.student_code = s.code and sbo.order_code = sbotm.order_code and sbotm.teach_material_id = tm.id AND s.spot_code = sp.code " +
        "and sbo.state > 3 and sbotm.is_send = 1 and sbotm.count > 0 and sbotm.price > 0 and s.spot_code = ?1 and sbo.semester_id = ?2 " +
        "UNION ALL " +
        "select sp.address, sp.admin_name, tm.name, tm.isbn, tm.author, sbotm.price, sbotm.count " +
        "from student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm, sync_student s, sync_spot sp " +
        "where sbo.student_code = s.code and sbo.id = sbotm.order_id and sbotm.teach_material_id = tm.id AND s.spot_code = sp.code " +
        "and sbo.state > 4 and sbotm.is_send = 1 and sbotm.count > 0 and sbotm.price > 0 and s.spot_code = ?1 and sbo.semester_id = ?2 " +
        "UNION ALL " +
        "select tmpo.address, tmpo.admin_name, tm.name, tm.isbn, tm.author, potm.tm_price price, potm.count " +
        "from teach_material_place_order tmpo, place_order_teach_material potm, teach_material tm " +
        "where tmpo.id = potm.order_id and potm.teach_material_id = tm.id " +
        "and tmpo.order_status > '3' and potm.is_send = 1 and potm.count > 0 and potm.tm_price > 0 and tmpo.spot_code = ?1 and tmpo.semester_id = ?2" +
        ") t group by address, admin_name, name, isbn, author, price order by address desc, admin_name desc, sum(count) desc")
    public List<Object[]> findSpotOrderTMInfo(String spotCode, long semesterId)throws Exception;


    /**
     * 查询学习中心有欠款的学生信息
     * @param spotCode
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select * from (" +
            "SELECT s.code,s.name, s.spec_code, s.level_code, sum(ifnull(se.pay, 0)) pay, sum(ifnull(se.buy, 0)) buy " +
            "FROM student_expense se " +
            "LEFT JOIN sync_student s ON se.student_code = s. CODE " +
            "WHERE s.spot_code = ?1 GROUP BY s.code, s.name, s.spec_code, s.level_code " +
            ") t where pay < buy ORDER BY pay-buy")
    public List<Object[]> findStudentOwnInfo(String spotCode)throws Exception;

    /**
     * 查询学习中心有余额的学生信息
     * @param spotCode
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select * from (" +
            "SELECT s.code,s.name, s.spec_code, s.level_code, sum(ifnull(se.pay, 0)) pay, sum(ifnull(se.buy, 0)) buy " +
            "FROM student_expense se " +
            "LEFT JOIN sync_student s ON se.student_code = s. CODE " +
            "WHERE s.spot_code = ?1 GROUP BY s.code, s.name, s.spec_code, s.level_code " +
            ") t where pay > buy ORDER BY pay-buy DESC")
    public List<Object[]> findStudentAccInfo(String spotCode)throws Exception;

    /**
     * 查询结清费用的学习中心
     * @return
     */
    @Query(nativeQuery = true, value = "select tt.*, s.year, s.quarter, ss.name from ( " +
            "select id, spot_code, ROUND(sum(buy), 2) buy, ROUND(sum(own), 2) own, (case when max(clear_time) = '2099-12-12 00:00:00' then null else max(clear_time) end) clearTime from  " +
            "( " +
            "select s.id, sp.code spot_code, sum(ifnull(se.buy, 0)) buy, sum(case when ifnull(se.pay, 0) - ifnull(se.buy, 0) > 0 then 0 else ifnull(se.buy, 0) - ifnull(se.pay, 0) end) own, IFNULL(seo.clear_time, '2099-12-12 00:00:00') clear_time  " +
            "from semester s INNER JOIN student_expense se on s.id = se.semester_id  " +
            "INNER JOIN sync_student stu on se.student_code = stu.code   " +
            "INNER JOIN sync_spot sp on stu.spot_code = sp.code   " +
            "INNER JOIN spot_expense_oth seo ON s.id = seo.semester_id and sp.code = seo.spot_code and s.year = ?1 " +
            "GROUP BY se.semester_id, sp.code   " +
            " " +
            "UNION ALL   " +
            " " +
            "select s.id, se.spot_code, IFNULL(se.buy,0) buy, CASE WHEN IFNULL(se.buy,0)-ifnull(se.pay,0) > 0 THEN IFNULL(se.buy,0)-IFNULL(se.pay,0) ELSE 0 END own,   " +
            "IFNULL(se.clear_time, '2099-12-12 00:00:00') clear_time   " +
            "from spot_expense se, semester s   " +
            "where se.semester_id = s.id and s.year = ?1 " +
            " " +
            ") t  " +
            "group by id, spot_code order by spot_code, id " +
            ") tt, semester s, sync_spot ss " +
            "where tt.buy > 0 and tt.id = s.id and tt.spot_code = ss.code " +
            "order by tt.spot_code, tt.id")
    public List<Object[]> countReward(int year)throws Exception;

    /**
     * 查询结清费用的学习中心
     * @return
     */
    @Query(nativeQuery = true, value = "select tt.*, s.year, s.quarter, ss.name from ( " +
            "select id, spot_code, ROUND(sum(buy), 2) buy, ROUND(sum(own), 2) own, (case when max(clear_time) = '2099-12-12 00:00:00' then null else max(clear_time) end) clearTime from  " +
            "( " +
            "select s.id, sp.code spot_code, sum(ifnull(se.buy, 0)) buy, sum(case when ifnull(se.pay, 0) - ifnull(se.buy, 0) > 0 then 0 else ifnull(se.buy, 0) - ifnull(se.pay, 0) end) own, IFNULL(seo.clear_time, '2099-12-12 00:00:00') clear_time  " +
            "from semester s INNER JOIN student_expense se on s.id = se.semester_id  " +
            "INNER JOIN sync_student stu on se.student_code = stu.code   " +
            "INNER JOIN sync_spot sp on stu.spot_code = sp.code   " +
            "INNER JOIN spot_expense_oth seo ON s.id = seo.semester_id and sp.code = seo.spot_code and s.year = ?1 " +
            "GROUP BY se.semester_id, sp.code   " +
            " " +
            "UNION ALL   " +
            " " +
            "select s.id, se.spot_code, IFNULL(se.buy,0) buy, CASE WHEN IFNULL(se.buy,0)-ifnull(se.pay,0) > 0 THEN IFNULL(se.buy,0)-IFNULL(se.pay,0) ELSE 0 END own,   " +
            "IFNULL(se.clear_time, '2099-12-12 00:00:00') clear_time   " +
            "from spot_expense se, semester s   " +
            "where se.semester_id = s.id and s.year = ?1 " +
            " " +
            ") t  " +
            "group by id, spot_code order by spot_code, id " +
            ") tt, semester s, sync_spot ss " +
            "where tt.buy > 0 and tt.id = s.id and tt.spot_code = ss.code and tt.spot_code = ?2 " +
            "order by tt.spot_code, tt.id")
    public List<Object[]> countReward(int year, String spotCode)throws Exception;

    /**
     * 查询一年一个中心的交费时间比例
     * @param year
     * @param spotCode
     * @param springBeginDate
     * @param springEndDate
     * @param autumnBeginDate
     * @param autumnEndDate
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select t.quarter, sum(t.pay), sum(t.pay2), sum(t.pay3), sum(t.pay4), sum(t.pay5), sum(t.pay6) from (" +
            "select s.quarter, " +
            "(CASE WHEN s.quarter = 0 and se.clear_time is not null and se.clear_time <= ?3 THEN sum(se.pay) END) pay, " +
            "(CASE WHEN s.quarter = 0 and se.clear_time is not null and se.clear_time > ?3 and se.clear_time <= ?4 THEN sum(se.pay) END)  pay2, " +
            "(CASE WHEN s.quarter = 0 and se.clear_time is not null and se.clear_time > ?4 THEN sum(se.pay) END)  pay3, " +
            "(CASE WHEN s.quarter = 1 and se.clear_time is not null and se.clear_time <= ?5 THEN sum(se.pay) END) pay4, " +
            "(CASE WHEN s.quarter = 1 and se.clear_time is not null and se.clear_time > ?5 and se.clear_time <= ?6 THEN sum(se.pay) END)  pay5, " +
            "(CASE WHEN s.quarter = 1 and se.clear_time is not null and se.clear_time > ?6 THEN sum(se.pay) END)  pay6 " +
            "from student_expense se, semester s, sync_student st " +
            "where se.student_code = st.code and se.semester_id = s.id and st.spot_code = ?2 and s.year = ?1 " +
            "group by quarter " +
            "union all " +
            "select s.quarter, " +
            "(CASE WHEN s.quarter = 0 and se.clear_time is not null and se.clear_time <= ?3 THEN sum(se.pay) END) pay, " +
            "(CASE WHEN s.quarter = 0 and se.clear_time is not null and se.clear_time > ?3 and se.clear_time <= ?4 THEN sum(se.pay) END)  pay2, " +
            "(CASE WHEN s.quarter = 0 and se.clear_time is not null and se.clear_time > ?4 THEN sum(se.pay) END)  pay3, " +
            "(CASE WHEN s.quarter = 1 and se.clear_time is not null and se.clear_time <= ?5 THEN sum(se.pay) END) pay4, " +
            "(CASE WHEN s.quarter = 1 and se.clear_time is not null and se.clear_time > ?5 and se.clear_time <= ?6 THEN sum(se.pay) END)  pay5, " +
            "(CASE WHEN s.quarter = 1 and se.clear_time is not null and se.clear_time > ?6 THEN sum(se.pay) END)  pay6 " +
            "from spot_expense se, semester s " +
            "where se.semester_id = s.id and se.spot_code = ?2 and s.year = ?1 " +
            "group by quarter" +
            ") t " +
            "group by t.quarter")
    public List<Object[]> countRewardForDate(int year, String spotCode, String springBeginDate, String springEndDate, String autumnBeginDate, String autumnEndDate)throws Exception;
}
