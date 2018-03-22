package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

/**
 * 拆分订单，把有库存的留起；库存不够的单独分出来成一个订单
 * Created by Allen on 2015/7/8.
 */
public interface SplitStudentBookOrderService extends EntityService<StudentBookOrder> {
    public void splitStudentBookOrder(long id, String loginName)throws Exception;

    public void splitStudentBookOrderForAll(String spotCode, String loginName)throws Exception;
}
