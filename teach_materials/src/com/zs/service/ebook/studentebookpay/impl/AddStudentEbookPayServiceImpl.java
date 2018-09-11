package com.zs.service.ebook.studentebookpay.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.ebook.studentebookpay.FindStudentEBookPayByCodeDAO;
import com.zs.dao.ebook.studentebookpay.StudentEBookPayDAO;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.service.ebook.studentebookpay.AddStudentEbookPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2018/1/5.
 */
@Service("addStudentEbookPayService")
public class AddStudentEbookPayServiceImpl extends EntityServiceImpl<StudentEBookPay, StudentEBookPayDAO> implements AddStudentEbookPayService {

    @Resource
    private FindStudentEBookPayByCodeDAO findStudentEBookPayByCodeDAO;

    @Override
    public void add(StudentEBookPay studentEBookPay) throws Exception {
        StudentEBookPay studentEBookPay2 = findStudentEBookPayByCodeDAO.find(studentEBookPay.getStudentCode());
        if(null != studentEBookPay2){
            throw new BusinessException("学号："+studentEBookPay.getStudentCode()+", 已经购买过电子教材");
        }
        //暂时关闭电子教材功能 2018-09-07
        //super.save(studentEBookPay);
    }
}
