package com.zs.service.orderbook.purchaseorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * 采购单管理查询
 * Created by Allen on 2015/5/12.
 */
public interface FindPurchaseOrderListByWhereService extends EntityService {
    public JSONArray getPurchaseOrderListByWhere(String semesterId, String channelId, String tmTypeId);
}
