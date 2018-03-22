package com.zs.dao.placeorder.placeordertm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/3/28.
 */
public interface DelPlaceOrderTMByOrderIdDAO extends EntityJpaDao<PlaceOrderTeachMaterial, Long> {
    @Modifying
    @Query("delete from PlaceOrderTeachMaterial where orderId = ?1 ")
    public void del(long orderId)throws Exception;
}
