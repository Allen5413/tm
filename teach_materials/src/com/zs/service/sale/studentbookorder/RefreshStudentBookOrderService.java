package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

/**
 * 库存不足的时候可以刷新订单，如果库存够了，就修改状态
 * Created by Allen on 2015/7/8.
 */
public interface RefreshStudentBookOrderService extends EntityService<StudentBookOrder> {
    public void refreshStudentBookOrder(long id, String loginName)throws Exception;

    public void refreshStudentBookOrderForAll(String loginName)throws Exception;
}
