package com.zs.service.orderbook.purchaseorderteachmaterial;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * 订单入库时，查询订单下的教材明细
 * Created by Allen on 2015/5/12.
 */
public interface FindPurchaseOrderTeachMaterialListByOrderCodeService extends EntityService {
    public JSONArray getPurchaseOrderTeachMaterialListByOrderCode(String orderCode, Long semesterId);
}
