package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询一个订单下面的教材数量，自动生成的订单
 * Created by Allen on 2015/8/4.
 */
public interface FindPlaceOrderTmCountAndPriceByOrderIdDAO extends EntityJpaDao<PlaceOrderTeachMaterial, Long> {
    @Query(nativeQuery = true,
            value = "SELECT sum(t.count), sum(totalPrice) FROM ( " +
                    "SELECT potm.count, tm.price * potm.count as totalPrice FROM " +
                    "place_order_teach_material potm, teach_material_course tmc, teach_material tm " +
                    "WHERE potm.course_code = tmc.course_code AND tmc.teach_material_id = tm.id AND tm.state = 0 " +
                    "AND potm.order_id = ?1 " +
                    "UNION ALL " +
                    "SELECT potm.count, tm.price * potm.count as totalPrice FROM " +
                    "place_order_teach_material potm, set_teach_material stm, set_teach_material_tm stmtm, teach_material tm " +
                    "WHERE potm.course_code = stm.buy_course_code AND stm.id = stmtm.set_teach_material_id " +
                    "AND stmtm.teach_material_id = tm.id AND tm.state = 0 AND potm.order_id = ?1) t")
    public Object[] findPlaceOrderTmCountAndPriceByOrderId(long orderId);
}
