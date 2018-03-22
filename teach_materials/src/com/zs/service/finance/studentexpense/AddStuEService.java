package com.zs.service.finance.studentexpense;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpense;

/**
 * 添加学生费用记录的接口
 * Created by Administrator on 2015/5/17.
 */
public interface AddStuEService extends EntityService<StudentExpense> {

    /**
     * 添加学生费用记录的方法
     * @param studentExpense
     * @param userName
     * @throws Exception
     */
    public void addStuE(StudentExpense studentExpense, String userName) throws Exception;
}
