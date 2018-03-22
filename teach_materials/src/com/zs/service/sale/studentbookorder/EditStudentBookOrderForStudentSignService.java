package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

/**
 * Created by Allen on 2015/9/10.
 */
public interface EditStudentBookOrderForStudentSignService extends EntityService<StudentBookOrder> {
    public void editStudentSign(String loginName, String... ids)throws Exception;
}
