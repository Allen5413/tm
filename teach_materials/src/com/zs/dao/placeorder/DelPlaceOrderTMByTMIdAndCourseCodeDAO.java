package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/8/3.
 */
public interface DelPlaceOrderTMByTMIdAndCourseCodeDAO extends EntityJpaDao<PlaceOrderTeachMaterial, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "DELETE place_order_teach_material FROM place_order_teach_material, teach_material_place_order WHERE teach_material_place_order.order_status < ?1 AND teach_material_place_order.order_code = place_order_teach_material.order_code AND place_order_teach_material.teach_material_id = ?2 AND place_order_teach_material.course_code = ?3")
    public void delPlaceOrderTMByTMIdAndCourseCode(String state, long tmId, String courseCode);
}
