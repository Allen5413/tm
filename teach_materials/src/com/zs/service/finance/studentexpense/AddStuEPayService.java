package com.zs.service.finance.studentexpense;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpense;

/**
 * 添加学生费用中已支付金额的方法
 * Created by LihongZhang on 2015/5/15.
 */
public interface AddStuEPayService extends EntityService<StudentExpense> {
    /**
     * 添加已支付金额的方法
     * @param changeMoney
     * @param studentCode
     * @throws Exception
     */
    public void addStuPay(Float changeMoney, String studentCode, String userName) throws Exception;
}
