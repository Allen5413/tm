package com.zs.dao.orderbook.purchaseorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.orderbook.PurchaseOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/5.
 */
public interface FindPurchaseOrderBySemesterIdDAO extends EntityJpaDao<PurchaseOrder,Long> {
    @Query("from PurchaseOrder where semesterId = ?1")
    public List<PurchaseOrder> getPurchaseOrderBySemesterId(Long semesterId);
}
