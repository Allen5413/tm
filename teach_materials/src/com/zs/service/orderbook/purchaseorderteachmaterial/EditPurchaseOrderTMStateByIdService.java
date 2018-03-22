package com.zs.service.orderbook.purchaseorderteachmaterial;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;

/**
 * 修改一条明细记录的状态
 * Created by Allen on 2015/7/7.
 */
public interface EditPurchaseOrderTMStateByIdService extends EntityService<PurchaseOrderTeachMaterial> {
    public void editPurchaseOrderTMStateById(long id, int state, String loginName)throws Exception;
}
