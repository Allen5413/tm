package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

import java.sql.Timestamp;

/**
 * Created by Allen on 2015/7/23.
 */
public interface EditStudentBookOrderForStateService extends EntityService<StudentBookOrder> {
    public void editStudentBookOrderForState(String orderCode, Long packageId, int state, Timestamp operateTime, String loginName)throws Exception;
}
