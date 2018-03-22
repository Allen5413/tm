package com.zs.service.orderbook.purchaseorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.orderbook.PurchaseOrder;

/**
 * 手动添加订购单
 * Created by Allen on 2015/7/11.
 */
public interface ManualAddPurchaseOrderService extends EntityService<PurchaseOrder> {
    public void addPurchaseOrderService(long issueChannelId, String idAndCounts, String loginName)throws Exception;
}
