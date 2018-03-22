package com.zs.service.orderbook.purchaseorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.orderbook.purchaseorder.PurchaseOrderDAO;
import com.zs.domain.orderbook.PurchaseOrder;
import com.zs.service.orderbook.purchaseorder.PurchaseOrderService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl extends EntityServiceImpl<PurchaseOrder, PurchaseOrderDAO> implements PurchaseOrderService {
}
