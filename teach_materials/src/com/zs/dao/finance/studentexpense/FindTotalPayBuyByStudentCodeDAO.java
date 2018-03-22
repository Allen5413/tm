package com.zs.dao.finance.studentexpense;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.StudentExpense;
import org.springframework.data.jpa.repository.Query;

/**
 * 统计一个学生的总的缴费和消费金额
 * Created by Allen on 2015/10/20.
 */
public interface FindTotalPayBuyByStudentCodeDAO extends EntityJpaDao<StudentExpense,Long> {

    @Query(nativeQuery = true, value = "select ifnull(sum(pay),0), ifnull(sum(buy),0) from student_expense where student_code = ?1")
    public Object[] find(String code);
}
