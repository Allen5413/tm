package com.zs.service.finance.studentexpensepay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpensePay;

/**
 * Created by Allen on 2016/1/11.
 */
public interface RefundStudentExpensePayByCodeService extends EntityService<StudentExpensePay> {
    /**
     *学生退款或者金额录多了，需要扣除
     * @throws Exception
     */
    public void refund(String code, double money, String userName) throws Exception;
}
