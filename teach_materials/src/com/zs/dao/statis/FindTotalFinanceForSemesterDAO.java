package com.zs.dao.statis;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.StudentExpense;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/1/14.
 */
public interface FindTotalFinanceForSemesterDAO extends EntityJpaDao<StudentExpense, Long> {
        /**
        * @return
        * @throws Exception
        */
        @Query(nativeQuery = true, value = "select t.year, t.quarter, t.buy+t2.buy buy, t.pay+t2.pay pay, t.own+t2.own own FROM (" +
            "select s.year, s.quarter, sum(buy) buy, sum(pay) pay, sum(CASE WHEN buy > pay THEN ROUND(buy-pay, 2) ELSE 0 END) own " +
            "from student_expense se, semester s " +
            "where se.semester_id = s.id " +
            "group by s.year, s.quarter " +
            "order by s.year, s.quarter " +
            ") t, " +
            "(select s.year, s.quarter, sum(buy) buy, sum(pay) pay, sum(CASE WHEN buy*discount/100 > pay THEN ROUND((buy*discount/100)-pay, 2) ELSE 0 END) own " +
            "from spot_expense se, semester s " +
            "where se.semester_id = s.id " +
            "group by s.year, s.quarter " +
            "order by s.year, s.quarter " +
            ") t2 " +
            "where t.year = t2.year and t.quarter = t2.quarter " +
            "group by year, quarter " +
            "order by year, quarter")
        public List<Object[]> find()throws Exception;

        /**
         * @return
         * @throws Exception
         */
        @Query(nativeQuery = true, value = "select tt.*, sp.name from " +
                "(" +
                "select t.spot_code, sum(t.buy) buy, sum(t.pay) pay, sum(t.own) own from (" +
                "(select st.spot_code, sum(buy) buy, sum(pay) pay, sum(CASE WHEN buy > pay THEN ROUND(buy-pay, 2) ELSE 0 END) own " +
                "from student_expense se, semester s, sync_student st " +
                "where se.semester_id = s.id and se.student_code = st.code and s.year = ?1 and s.quarter = ?2 " +
                "group by st.spot_code " +
                "order by st.spot_code " +
                ") " +
                "UNION ALL " +
                "(select se.spot_code, sum(buy) buy, sum(pay) pay, sum(CASE WHEN buy*discount/100 > pay THEN ROUND((buy*discount/100)-pay, 2) ELSE 0 END) own " +
                "from spot_expense se, semester s " +
                "where se.semester_id = s.id and s.year = ?1 and s.quarter = ?2 " +
                "group by se.spot_code " +
                "order by se.spot_code " +
                ") " +
                ") t " +
                "group by t.spot_code " +
                "order by t.spot_code " +
                ") tt, sync_spot sp where tt.spot_code = sp.code and tt.own > 0")
        public List<Object[]> findBySemesterForSpot(int year, int quarter)throws Exception;
}

