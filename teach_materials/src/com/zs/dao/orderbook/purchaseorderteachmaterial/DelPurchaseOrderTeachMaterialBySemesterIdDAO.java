package com.zs.dao.orderbook.purchaseorderteachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/5.
 */
public interface DelPurchaseOrderTeachMaterialBySemesterIdDAO extends EntityJpaDao<PurchaseOrderTeachMaterial,Long> {
    @Modifying
    @Query("delete from PurchaseOrderTeachMaterial where semesterId = ?1")
    public void delPurchaseOrderTeachMaterialBySemesterId(Long semesterId);
}
