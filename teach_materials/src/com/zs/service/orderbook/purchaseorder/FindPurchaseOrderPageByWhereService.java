package com.zs.service.orderbook.purchaseorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.orderbook.PurchaseOrder;

import java.util.Map;

/**
 * Created by Allen on 2015/5/5.
 */
public interface FindPurchaseOrderPageByWhereService extends EntityService<PurchaseOrder> {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
