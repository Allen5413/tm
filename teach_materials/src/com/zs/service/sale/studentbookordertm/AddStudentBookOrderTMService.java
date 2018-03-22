package com.zs.service.sale.studentbookordertm;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrderTM;

/**
 * Created by Allen on 2015/7/14.
 */
public interface AddStudentBookOrderTMService extends EntityService<StudentBookOrderTM> {
    public void AddStudentBookOrderTM(String orderCode, String idAndCounts, String loginName)throws Exception;
}
