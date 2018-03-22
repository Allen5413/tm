package com.zs.service.sale.oncepurchaseorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.OncePurchaseOrder;

/**
 * Created by Allen on 2016/6/21.
 */
public interface AddOncePurchaseOrderService extends EntityService<OncePurchaseOrder> {
    public void add(String loginName)throws Exception;
}
