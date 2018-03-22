package com.zs.service.orderbook.purchaseorderteachmaterial.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.orderbook.purchaseorderteachmaterial.PurchaseOrderTeachMaterialDAO;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.service.orderbook.purchaseorderteachmaterial.PurchaseOrderTeachMaterialService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("PurchaseOrderTeachMaterialService")
public class PurchaseOrderTeachMaterialServiceImpl extends EntityServiceImpl<PurchaseOrderTeachMaterial, PurchaseOrderTeachMaterialDAO> implements PurchaseOrderTeachMaterialService {
}
