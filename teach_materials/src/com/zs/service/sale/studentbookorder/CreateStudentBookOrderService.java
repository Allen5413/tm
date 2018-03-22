package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

/**
 * 生成学生订书单
 * Created by Allen on 2015/5/12.
 */
public interface CreateStudentBookOrderService extends EntityService<StudentBookOrder> {
    public void addStudentBookOrder(String loginName)throws Exception;
}
