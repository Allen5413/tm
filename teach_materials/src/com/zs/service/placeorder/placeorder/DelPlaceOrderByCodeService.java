package com.zs.service.placeorder.placeorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;

/**
 * Created by Allen on 2016/3/28.
 */
public interface DelPlaceOrderByCodeService extends EntityService<TeachMaterialPlaceOrder> {
    public void del(Long... ids)throws Exception;
}
