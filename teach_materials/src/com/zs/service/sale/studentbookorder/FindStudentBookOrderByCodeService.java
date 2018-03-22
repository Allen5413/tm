package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

/**
 * Created by Allen on 2015/7/22.
 */
public interface FindStudentBookOrderByCodeService extends EntityService<StudentBookOrder> {
    public StudentBookOrder findStudentBookOrderByCode(String orderCode);
}
