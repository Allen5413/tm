package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;

/**
 * 取消确认订单
 * Created by Allen on 2015/7/16.
 */
public interface CancelOnceOrderService extends EntityService<StudentBookOnceOrder> {
    public void cancelOnceOrder(Long[] ids, String loginName)throws Exception;
}
