package com.zs.service.ebook.studentebookpay;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.ebook.StudentEBookPay;

/**
 * Created by Allen on 2018/1/5.
 */
public interface EditStudentEbookPayForQsService extends EntityService<StudentEBookPay> {
    public void edit(String code, String hr, String errorMsg, String qsRemark, String qsOrderCode)throws Exception;
}
