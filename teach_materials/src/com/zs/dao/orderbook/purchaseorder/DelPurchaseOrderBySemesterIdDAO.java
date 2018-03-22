package com.zs.dao.orderbook.purchaseorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.orderbook.PurchaseOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/5.
 */
public interface DelPurchaseOrderBySemesterIdDAO extends EntityJpaDao<PurchaseOrder,Long> {
    @Modifying
    @Query("delete from PurchaseOrder where semesterId = ?1")
    public void delPurchaseOrderBySemesterId(Long semesterId);
}