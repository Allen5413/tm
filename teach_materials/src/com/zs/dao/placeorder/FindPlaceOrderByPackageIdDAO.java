package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/8/2.
 */
public interface FindPlaceOrderByPackageIdDAO extends EntityJpaDao<TeachMaterialPlaceOrder, Long> {
    @Query("FROM TeachMaterialPlaceOrder where packageId = ?1")
    public List<TeachMaterialPlaceOrder> findPlaceOrderByPackageId(long packageId);
}
