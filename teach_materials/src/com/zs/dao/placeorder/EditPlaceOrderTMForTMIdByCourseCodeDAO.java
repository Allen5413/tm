package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 修改预订单明细课程对应的教材
 * Created by Allen on 2015/8/3.
 */
public interface EditPlaceOrderTMForTMIdByCourseCodeDAO extends EntityJpaDao<PlaceOrderTeachMaterial, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE place_order_teach_material potm INNER JOIN teach_material_place_order tmpo ON potm.order_id = tmpo.id SET potm.teach_material_id = ?1, potm.tm_price = ?2 WHERE potm.course_code = ?3 AND tmpo.order_status < ?4")
    public void delPlaceOrderTMByTMIdAndCourseCode(int state, long tmId, String courseCode);
}
