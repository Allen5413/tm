package com.zs.service.finance.studentexpense.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.finance.studentexpense.FindRecordStudentCodeDao;
import com.zs.dao.finance.studentexpense.StudentExpenseDao;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.finance.StudentExpense;
import com.zs.service.finance.studentexpense.AddStuEService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 添加学生费用记录接口的实现类
 * Created by LihongZhang on 2015/5/17.
 */
@Service("addStuEService")
public class AddStuEServiceImpl extends EntityServiceImpl<StudentExpense,StudentExpenseDao>  implements AddStuEService {

    @Resource
    private FindRecordStudentCodeDao findRecordStudentCodeDao;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;

    @Override
    public void addStuE(StudentExpense studentExpense, String userName) throws Exception {
        if (null != studentExpense){
            //获取当前学期
            Semester semester = findNowSemesterDAO.getNowSemester();
            //验证学生学号是否存在
            if (null == findStudentByCodeDAO.getStudentByCode(studentExpense.getStudentCode())){
                throw new BusinessException("该学号不存在");
            }
            //验证是否存在该学生的费用记录
            if (null != findRecordStudentCodeDao.getRecordByStuCode(studentExpense.getStudentCode(), semester.getId())){
                throw new BusinessException("该学生费用记录已存在");
            }
            studentExpense.setSemesterId(semester.getId());
            //添加状态
            studentExpense.setState(StudentExpense.STATE_NO);
            //添加创建人和操作人
            studentExpense.setCreator(userName);
            studentExpense.setOperator(userName);
            //执行添加
            findRecordStudentCodeDao.save(studentExpense);
        }else {
            throw new BusinessException("没有传入学生费用信息");
        }
    }
}
