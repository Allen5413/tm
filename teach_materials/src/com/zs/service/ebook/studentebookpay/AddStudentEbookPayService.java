package com.zs.service.ebook.studentebookpay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.StudentEBookPay;

/**
 * Created by Allen on 2018/1/5.
 */
public interface AddStudentEbookPayService extends EntityService<StudentEBookPay> {
    public void add(StudentEBookPay studentEBookPay)throws Exception;
}
