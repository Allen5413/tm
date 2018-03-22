package com.zs.service.ebook.studentebookpay.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.ebook.studentebookpay.FindStudentEBookPayByCodeDAO;
import com.zs.dao.ebook.studentebookpay.StudentEBookPayDAO;
import com.zs.domain.ebook.StudentEBookPay;
import com.zs.service.ebook.studentebookpay.EditStudentEbookPayForQsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2018/1/5.
 */
@Service("editStudentEbookPayForQsService")
public class EditStudentEbookPayForQsServiceImpl extends EntityServiceImpl<StudentEBookPay, StudentEBookPayDAO> implements EditStudentEbookPayForQsService {

    @Resource
    private FindStudentEBookPayByCodeDAO findStudentEBookPayByCodeDAO;


    @Override
    public void edit(String code, String hr, String errorMsg, String qsRemark, String qsOrderCode) throws Exception {
        StudentEBookPay studentEBookPay = findStudentEBookPayByCodeDAO.find(code);
        if(null != studentEBookPay){
            studentEBookPay.setHr(hr);
            studentEBookPay.setErrorMsg(errorMsg);
            studentEBookPay.setQsRemark(qsRemark);
            studentEBookPay.setQsOrderCode(qsOrderCode);
            super.save(studentEBookPay);
        }
    }
}
