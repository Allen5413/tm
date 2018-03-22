package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

/**
 * 取消确认订单
 * Created by Allen on 2015/7/16.
 */
public interface CancelStudentBookOrderService extends EntityService<StudentBookOrder> {
    public void cancelStudentBookOrder(Long[] ids, String loginName)throws Exception;
}
