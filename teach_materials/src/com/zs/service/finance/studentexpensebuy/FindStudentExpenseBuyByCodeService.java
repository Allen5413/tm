package com.zs.service.finance.studentexpensebuy;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpenseBuy;

import java.util.List;
import java.util.Map;


/**
 * Created by Allen on 2015/5/25.
 */
public interface FindStudentExpenseBuyByCodeService extends EntityService<StudentExpenseBuy> {
    public Map<String, Map<Double, List<StudentExpenseBuy>>> getStudentExpenseBuyByCode(String code);
}
