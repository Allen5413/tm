package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;

/**
 * Created by Allen on 2015/12/9 0009.
 */
public interface FindPlaceOrderByCodeService extends EntityService{
    public TeachMaterialPlaceOrder findByCode(String orderCode)throws Exception;
}
