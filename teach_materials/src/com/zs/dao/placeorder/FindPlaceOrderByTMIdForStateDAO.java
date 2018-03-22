package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/9/8.
 */
public interface FindPlaceOrderByTMIdForStateDAO extends EntityJpaDao<TeachMaterialPlaceOrder, Long> {
    @Query("select tmpo from TeachMaterialPlaceOrder tmpo, PlaceOrderTeachMaterial potm, TeachMaterialCourse tmc " +
            "where tmpo.id = potm.orderId and potm.courseCode = tmc.courseCode and tmc.teachMaterialId = ?1 and tmpo.orderStatus BETWEEN ?2 and ?3 UNION ALL " +
            "select tmpo from TeachMaterialPlaceOrder tmpo, PlaceOrderTeachMaterial potm, SetTeachMaterial stm, SetTeachMaterialTM stmtm " +
            "where tmpo.id = potm.orderId and potm.courseCode = stm.buyCourseCode and stm.id = stmtm.setTeachMaterialId " +
            "and stmtm.teachMaterialId = ?1 and tmpo.orderStatus BETWEEN ?2 and ?3 UNION ALL " +
            "select tmpo from TeachMaterialPlaceOrder tmpo, PlaceOrderTeachMaterial potm " +
            "where tmpo.id = potm.orderId and potm.teachMaterialId = ?1 and tmpo.orderStatus BETWEEN ?2 and ?3")
    public List<TeachMaterialPlaceOrder> findPlaceOrderByTMIdForStateDAO(long tmId, String state, String state2);
}
