package com.zs.dao.finance.studentexpense;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.StudentExpense;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/9.
 */
public interface FindStudentPayTotalBySpotCodeDAO extends EntityJpaDao<StudentExpense, Long> {
    @Query(nativeQuery = true, value = "SELECT ifnull(sum(se.pay),0) FROM student_expense se, sync_student s where se.student_code = s.code and s.spot_code = ?1")
    public double find(String spotCode);
}
