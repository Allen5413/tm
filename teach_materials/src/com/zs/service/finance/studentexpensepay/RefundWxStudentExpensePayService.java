package com.zs.service.finance.studentexpensepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpensePay;

/**
 * Created by Allen on 2016/6/27.
 */
public interface RefundWxStudentExpensePayService extends EntityService<StudentExpensePay> {
    public void refund(String code, double money, String loginName)throws Exception;
}
