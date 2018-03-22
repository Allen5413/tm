package com.zs.service.finance.studentexpense;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpense;

/**
 * 用于减少学生费用中已支付金额的接口
 * Created by LihongZhang on 2015/5/15.
 */
public interface SubStuEPayService extends EntityService<StudentExpense>{

    /**
     * 用于减少学生费用中已支付金额的方法
     * @param changeMoney
     * @param studentCode
     * @throws Exception
     */
    public void subStuEPay(Float changeMoney, String studentCode, String userName) throws Exception;
}
