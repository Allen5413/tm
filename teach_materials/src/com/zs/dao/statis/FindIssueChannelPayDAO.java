package com.zs.dao.statis;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 统计发行商应付款
 * Created by Allen on 2015/9/29.
 */
public interface FindIssueChannelPayDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Query(nativeQuery = true, value = "select year, case when quarter = 0 then '上学期' else '下学期' end, name, sum(tt.a) zk, sum(tt.b) wljc, sum(tt.c) zb, sum(tt.d) td, sid, id  " +
            "from (select t.sid, t.id, t.year, t.quarter, t.name, " +
            "sum(if(t.typeId = 1, t.price, 0)) a, " +
            "sum(if(t.typeId = 2, t.price, 0)) b, " +
            "sum(if(t.typeId = 3, t.price, 0)) c, " +
            "sum(if(t.typeId = 4, t.price, 0)) d " +
            "from (select se.id sid, ic.id, se.year, se.quarter, ic.name, tm.teach_material_type_id typeId, sum(sbotm.price * sbotm.count) price " +
            "from student_book_order sbo, student_book_order_tm sbotm, semester se, issue_channel ic, teach_material tm " +
            "where sbo.order_code = sbotm.order_code and sbo.issue_channel_id = ic.id and sbo.semester_id = se.id and sbotm.teach_material_id = tm.id " +
            "and sbo.state > 3 and sbotm.count > 0 and sbotm.is_send = 1 " +
            "group by sid, id, se.year, se.quarter, ic.name, tm.teach_material_type_id) t " +
            "group by t.sid, t.id, t.year, t.quarter, t.name " +
            "union all " +
            "select t.sid, t.id, t.year, t.quarter, t.name, " +
            "sum(if(t.typeId = 1, t.price, 0)) a, " +
            "sum(if(t.typeId = 2, t.price, 0)) b, " +
            "sum(if(t.typeId = 3, t.price, 0)) c, " +
            "sum(if(t.typeId = 4, t.price, 0)) d " +
            "from (select se.id sid, ic.id, se.year, se.quarter, ic.name, tm.teach_material_type_id typeId, sum(potm.tm_price * potm.count) price " +
            "from teach_material_place_order tmpo, place_order_teach_material potm, semester se, issue_channel ic, teach_material tm, issue_range ir " +
            "where tmpo.id = potm.order_id and ir.issue_channel_id = ic.id and tmpo.semester_id = se.id and potm.teach_material_id = tm.id and tmpo.spot_code = ir.spot_code " +
            "and tmpo.order_status > '3' and potm.count > 0 and potm.is_send = 1 " +
            "group by sid, id, se.year, se.quarter, ic.name, tm.teach_material_type_id) t " +
            "group by t.sid, t.id, t.year, t.quarter, t.name " +
            "union all " +
            "select t.sid, t.id, t.year, t.quarter, t.name, " +
            "sum(if(t.typeId = 1, t.price, 0)) a, " +
            "sum(if(t.typeId = 2, t.price, 0)) b, " +
            "sum(if(t.typeId = 3, t.price, 0)) c, " +
            "sum(if(t.typeId = 4, t.price, 0)) d " +
            "from (select se.id sid, ic.id, se.year, se.quarter, ic.name, tm.teach_material_type_id typeId, sum(sbotm.price * sbotm.count) price " +
            "from student_book_once_order sbo, student_book_once_order_tm sbotm, semester se, issue_channel ic, teach_material tm " +
            "where sbo.id = sbotm.order_id and sbo.issue_channel_id = ic.id and sbo.semester_id = se.id and sbotm.teach_material_id = tm.id " +
            "and sbo.state > 4 and sbotm.count > 0 and sbotm.is_send = 1 " +
            "group by sid, id, se.year, se.quarter, ic.name, tm.teach_material_type_id) t " +
            "group by t.sid, t.id, t.year, t.quarter, t.name " +
            ") tt " +
            "group by year, quarter, name, sid, id")
    public List<Object[]> findIssueChannelPay()throws Exception;
}
