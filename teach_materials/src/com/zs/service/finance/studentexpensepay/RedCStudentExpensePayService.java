package com.zs.service.finance.studentexpensepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpensePay;

/**
 * Created by Allen on 2016/6/27.
 */
public interface RedCStudentExpensePayService extends EntityService<StudentExpensePay> {
    public void redC(long id, String remark, String money, String loginName)throws Exception;
}
