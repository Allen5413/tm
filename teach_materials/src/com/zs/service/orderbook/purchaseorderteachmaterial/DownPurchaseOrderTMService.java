package com.zs.service.orderbook.purchaseorderteachmaterial;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/6/3.
 */
public interface DownPurchaseOrderTMService extends EntityService {
    public String downPurchaseOrderTM(String orderCode, Long semesterId, Long channelId, String fileName)throws Exception;
}
