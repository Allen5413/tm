package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 修改预订单明细的价格。
 * 教材价格变更后，会把已发送状态前的订单明细价格一起改动
 * Created by Allen on 2015/8/6.
 */
public interface EditPlaceOrderTMForPriceDAO extends EntityJpaDao<PlaceOrderTeachMaterial, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE place_order_teach_material potm SET potm.tm_price = ?1 WHERE EXISTS (SELECT tmpo.id FROM teach_material_place_order tmpo WHERE potm.order_id = tmpo.id AND tmpo.order_status < ?2) AND potm.teach_material_id = ?3")
    public void editPlaceOrderTMForPrice(float price, String state, Long tmId);
}
