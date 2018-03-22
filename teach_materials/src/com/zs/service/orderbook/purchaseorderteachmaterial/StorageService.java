package com.zs.service.orderbook.purchaseorderteachmaterial;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;

import java.util.Map;

/**
 * 订书单入库操作接口
 * Created by Allen on 2015/5/5.
 */
public interface StorageService extends EntityService<PurchaseOrderTeachMaterial> {
    public void storage(Long[] ids, String[] counts, Long channelId, String loginName)throws Exception;
}
