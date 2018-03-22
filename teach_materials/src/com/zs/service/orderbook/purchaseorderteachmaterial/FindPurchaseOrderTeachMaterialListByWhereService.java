package com.zs.service.orderbook.purchaseorderteachmaterial;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * 订单入库查询
 * Created by Allen on 2015/5/12.
 */
public interface FindPurchaseOrderTeachMaterialListByWhereService extends EntityService {
    public JSONArray getPurchaseOrderTeachMaterialListByWhere(String semesterId, String channelId, String tmTypeId);
}
