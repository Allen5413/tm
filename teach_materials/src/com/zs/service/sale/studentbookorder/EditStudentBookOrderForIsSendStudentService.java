package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

/**
 * Created by Allen on 2017/4/13.
 */
public interface EditStudentBookOrderForIsSendStudentService extends EntityService<StudentBookOrder> {
    public void edit(String orderCode, int isSendStudent, String address, String sendPhone, String sendZipCode, String loginName)throws Exception;
}
