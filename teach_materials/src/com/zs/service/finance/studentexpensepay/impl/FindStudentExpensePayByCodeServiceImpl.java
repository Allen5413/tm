package com.zs.service.finance.studentexpensepay.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpensepay.FindStudentExpensePayByCodeDAO;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.studentexpensepay.FindStudentExpensePayByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/25.
 */
@Service("findStudentExpensePayByCodeService")
public class FindStudentExpensePayByCodeServiceImpl
        extends EntityServiceImpl<StudentExpensePay, FindStudentExpensePayByCodeDAO>
        implements FindStudentExpensePayByCodeService{

    @Resource
    private FindStudentExpensePayByCodeDAO findStudentExpensePayByCodeDAO;

    @Override
    public List<StudentExpensePay> getStudentExpensePayByCode(String code) {
        return findStudentExpensePayByCodeDAO.getStudentExpensePayByCode(code);
    }
}
