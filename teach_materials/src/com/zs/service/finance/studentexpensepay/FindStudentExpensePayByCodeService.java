package com.zs.service.finance.studentexpensepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpensePay;

import java.util.List;

/**
 * Created by Allen on 2015/5/25.
 */
public interface FindStudentExpensePayByCodeService extends EntityService<StudentExpensePay> {
    public List<StudentExpensePay> getStudentExpensePayByCode(String code);
}
