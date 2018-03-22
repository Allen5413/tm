package com.zs.service.finance.studentexpensebuy;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.StudentExpenseBuy;

/**
 * 添加学生费用消费记录的业务逻辑接口
 * Created by LihongZhang on 2015/5/14.
 */
public interface AddStudentExpenseBuyService extends EntityService<StudentExpenseBuy> {


    /**
     * 添加学生消费记录的方法
     * 主要逻辑是，添加学生消费记录，然后在学生费用中减去消费金额
     * @param studentExpenseBuy
     * @param userName
     * @throws Exception
     */
    public void addStudentExpenseBuy(StudentExpenseBuy studentExpenseBuy, String userName) throws Exception;

}
