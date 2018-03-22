package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 修改一个订单的明细都为已发出
 * Created by Allen on 2016/1/14 0014.
 */
public interface EditPlaceOrderTMByOrderIdForIsSendDAO  extends EntityJpaDao<PlaceOrderTeachMaterial, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE place_order_teach_material potm SET potm.is_send = 1, operator = ?1, operate_time = now() WHERE potm.order_id = ?2")
    public void edit(String userName, Long orderId);
}
