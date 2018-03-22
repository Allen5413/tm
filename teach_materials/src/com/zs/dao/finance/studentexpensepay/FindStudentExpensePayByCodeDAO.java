package com.zs.dao.finance.studentexpensepay;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.StudentExpensePay;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/25.
 */
public interface FindStudentExpensePayByCodeDAO extends EntityJpaDao<StudentExpensePay, Long> {
    @Query("from StudentExpensePay where studentCode = ?1 order by createTime desc")
    public List<StudentExpensePay> getStudentExpensePayByCode(String code);
}
