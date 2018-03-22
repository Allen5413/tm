package com.zs.service.finance.studentexpense.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.finance.studentexpense.FindRecordStudentCodeDao;
import com.zs.dao.finance.studentexpense.StudentExpenseDao;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.finance.StudentExpense;
import com.zs.service.finance.studentexpense.SubStuEPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 减少学生费用已支付金额接口的实现类
 * Created by LihongZhang on 2015/5/15.
 */
@Service("subStuEPayService")
public class SubStuEPayServiceImpl extends EntityServiceImpl<StudentExpense,StudentExpenseDao> implements SubStuEPayService{


    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindRecordStudentCodeDao findRecordStudentCodeDao;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void subStuEPay(Float changeMoney, String studentCode,String userName) throws Exception {
        //获取当前学期
        Semester semester = findNowSemesterDAO.getNowSemester();

        //验证学生学号是否存在
        if (null == findStudentByCodeDAO.getStudentByCode(studentCode)){
            throw new BusinessException("该学生不存在");
        }
        //验证金额不能为负
        if ( 0 > changeMoney){
            throw new BusinessException("操作的金额数不能为负");
        }
        StudentExpense studentExpense = findRecordStudentCodeDao.getRecordByStuCode(studentCode, semester.getId());
        if (null == studentExpense){
            throw new BusinessException("该学生不存在");
        }
        //修改金额
        studentExpense.setPay(studentExpense.getPay()-changeMoney);
        //写入操作人
        studentExpense.setOperator(userName);
        //创建人和创建时间不能变
        studentExpense.setCreator(studentExpense.getCreator());
        studentExpense.setCreateTime(studentExpense.getCreateTime());
        //版本号设置
        studentExpense.setVersion(studentExpense.getVersion());
        //执行修改
        findRecordStudentCodeDao.update(studentExpense);
    }
}
