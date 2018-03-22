package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderTeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

/**
 * 查询一个渠道下的一本教材在已生成还未发货的预订单中的数量
 * Created by Allen on 2015/7/31.
 */
public interface FindTMCountForConfirmPlaceOrderDAO extends EntityJpaDao<PlaceOrderTeachMaterial, Long> {
    @Query(nativeQuery = true,
            value = "SELECT t.count + t2.count + t3.count FROM " +
                    "(SELECT CASE WHEN sum(potm.count) IS NULL THEN 0 ELSE sum(potm.count) END AS count " +
                    "FROM issue_range ir, teach_material_place_order tmpo, place_order_teach_material potm, teach_material_course tmc " +
                    "WHERE ir.spot_code = tmpo.spot_code AND tmpo.id = potm.order_id AND tmc.course_code = potm.course_code AND tmpo.order_status < '5' AND tmpo.is_stock = 0 AND tmpo.is_spec_all = 0 " +
                    "AND tmc.teach_material_id = ?1 AND ir.issue_channel_id = ?2 AND tmpo.semester_id = ?3) t, " +
                    "(SELECT CASE WHEN sum(potm.count) IS NULL THEN 0 ELSE sum(potm.count) END AS count " +
                    "FROM issue_range ir, teach_material_place_order tmpo, place_order_teach_material potm, set_teach_material stm, set_teach_material_tm stmtm " +
                    "WHERE ir.spot_code = tmpo.spot_code AND stmtm.set_teach_material_id = stm.id AND stm.buy_course_code = potm.course_code AND potm.order_id = tmpo.id AND tmpo.order_status < '5'  AND tmpo.is_stock = 0 AND tmpo.is_spec_all = 0 " +
                    "AND stmtm.teach_material_id = ?1 AND ir.issue_channel_id = ?2 AND tmpo.semester_id = ?3) t2, " +
                    "(SELECT CASE WHEN sum(potm.count) IS NULL THEN 0 ELSE sum(potm.count) END AS count " +
                    "FROM issue_range ir, teach_material_place_order tmpo, place_order_teach_material potm " +
                    "WHERE ir.spot_code = tmpo.spot_code AND tmpo.id = potm.order_id AND tmpo.order_status < '5'  AND tmpo.is_stock = 0 AND tmpo.is_spec_all = 0 " +
                    "AND potm.teach_material_id = ?1 AND ir.issue_channel_id = ?2 AND tmpo.semester_id = ?3) t3")
    public BigDecimal findTMCountForConfirmPlaceOrder(long teachMaterialId, long issueChannelId, long semesterId);
}
