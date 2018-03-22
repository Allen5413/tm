package com.zs.dao.sale.oncepurchaseorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.OncePurchaseOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询要生成采购单的教材数据
 * Created by Allen on 2016/6/21.
 */
public interface FindAddOncePurchaeOrderTMListDAO extends EntityJpaDao<OncePurchaseOrder, Long> {
    @Query(nativeQuery = true, value = "select t.issue_channel_id, t.teach_material_type_id, t.course_code, t.id, sum(t.count) from (" +
            "SELECT sbo.issue_channel_id, tm.teach_material_type_id, sbotm.course_code, tm.id, sum(sbotm.count) count " +
            "FROM student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm " +
            "where sbo.id = sbotm.order_id and sbotm.teach_material_id = tm.id " +
            "and sbo.state = 1 and sbotm.count > 0 and tm.state = 0 " +
            "group by sbo.issue_channel_id, tm.teach_material_type_id, sbotm.course_code, tm.id " +
            "UNION ALL " +
            "SELECT ir.issue_channel_id, tm.teach_material_type_id, potm.course_code, tm.id, sum(potm.count) count " +
            "FROM issue_range ir, teach_material_place_order tmpo, place_order_teach_material potm, teach_material_course tmc, teach_material tm " +
            "WHERE tmpo.id = potm.order_id AND tmpo.spot_code = ir.spot_code AND potm.course_code = tmc.course_code AND tmc.teach_material_id = tm.id " +
            "AND tmpo.order_status = '1' AND tmpo.is_spec_all = 1 AND potm.count > 0 and tm.state = 0 " +
            "GROUP BY ir.issue_channel_id, tm.teach_material_type_id, potm.course_code, tm.id "+
            "UNION ALL " +
            "SELECT ir.issue_channel_id, tm.teach_material_type_id, potm.course_code, tm.id, sum(potm.count) count " +
            "FROM issue_range ir, teach_material_place_order tmpo, place_order_teach_material potm, set_teach_material stm, set_teach_material_tm stmtm, teach_material tm " +
            "WHERE tmpo.id = potm.order_id AND tmpo.spot_code = ir.spot_code AND potm.course_code = stm.buy_course_code AND stm.id = stmtm.set_teach_material_id " +
            "AND stmtm.teach_material_id = tm.id AND tmpo.order_status = '1' AND tmpo.is_spec_all = 1 AND potm.count > 0 and tm.state = 0 " +
            "GROUP BY ir.issue_channel_id, tm.teach_material_type_id, potm.course_code, tm.id " +
            ") t " +
            "group by t.issue_channel_id, t.teach_material_type_id, t.course_code, t.id")
    public List<Object[]> find();
}
