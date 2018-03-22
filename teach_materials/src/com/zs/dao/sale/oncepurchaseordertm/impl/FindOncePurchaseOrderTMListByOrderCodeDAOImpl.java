package com.zs.dao.sale.oncepurchaseordertm.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 订单入库时，通过订单编号，查询教材信息
 * Created by Allen on 2015/5/12.
 */
@Service("findOncePurchaseOrderTMListByOrderCodeDAO")
public class FindOncePurchaseOrderTMListByOrderCodeDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("select case when count(t2.name) = 1 then t2.name else '公共' end as deptName, t.* from ");
        sbSql.append("(SELECT " +
                "potm.id, " +
                "potm.course_code, " +
                "potm.state, " +
                "p. NAME pressName, " +
                "tm.isbn, " +
                "tm. NAME tmName, " +
                "tm.author, " +
                "tm.price, " +
                "ifnull(ceil(potm.teach_material_count*1.2), 0) tmCount, " +
                "ifnull(potm.put_storage_count, 0) storageCount, " +
                "ifnull(tms.stock, 0) stock, " +
                "ifnull(sorder.sCount, 0) sCount, " +
                "ifnull(porder.pCount, 0) pCount, " +
                "potm.operator, " +
                "potm.operate_time " +
                "FROM " +
                "once_purchase_order po INNER JOIN once_purchase_order_tm potm on po.code = potm.code " +
                "INNER JOIN teach_material tm on potm.teach_material_id = tm.id " +
                "LEFT JOIN teach_material_stock tms on po.issue_channel_id = tms.issue_channel_id and tm.id = tms.teach_material_id " +
                "INNER JOIN press p on tm.press_id = p.id " +
                "LEFT JOIN " +
                "(select sbo.semester_id, sbo.issue_channel_id, sbotm.teach_material_id, sum(sbotm.count) sCount from student_book_order sbo, student_book_order_tm sbotm  " +
                "where sbo.order_code = sbotm.order_code and sbo.state > 0 and sbo.state < 4 and sbotm.count > 0 " +
                "group by sbo.semester_id, sbo.issue_channel_id, sbotm.teach_material_id) sorder on po.semester_id = sorder.semester_id and po.issue_channel_id = sorder.issue_channel_id " +
                "and potm.teach_material_id = sorder.teach_material_id " +
                "LEFT JOIN " +
                "(select tmpo.semester_id, ir.issue_channel_id, potm.teach_material_id, sum(potm.count) pCount  " +
                "from teach_material_place_order tmpo, place_order_teach_material potm, issue_range ir " +
                "where tmpo.spot_code = ir.spot_code and tmpo.id = potm.order_id and tmpo.order_status > 0 and tmpo.order_status < 4 and potm.count > 0 " +
                "group by tmpo.semester_id, ir.issue_channel_id, potm.teach_material_id) porder on po.semester_id = porder.semester_id and po.issue_channel_id = porder.issue_channel_id " +
                "and potm.teach_material_id = porder.teach_material_id " +
                "WHERE potm.CODE = ?) t ");
        sbSql.append("left join ");
        sbSql.append("(select dept.name, bs.course_code from semester s, sync_begin_schedule bs, sync_spec spec, sync_department dept where s.year = bs.academic_year and s.quarter = bs.term and s.id = ? and bs.spec_code = spec.code and spec.dept_code = dept.code group by dept.name, bs.course_code) t2 ");
        sbSql.append("on t.course_code = t2.course_code ");
        sbSql.append("group by id, course_code, pressName, isbn, tmName, author, tmCount, storageCount, operator, operate_time order by deptName");

        List<Object> params = new ArrayList<Object>();
        String orderCode = paramsMap.get("orderCode");
        String semesterId = paramsMap.get("semesterId");
        params.add(orderCode);
        params.add(semesterId);
        if(null != sortMap) {
            sbSql.append("order by ");
            int i = 0;
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                if(0 < i){
                    sbSql.append(",");
                }
                String key = it.next().toString();
                sbSql.append(key);
                sbSql.append(" ");
                sbSql.append(sortMap.get(key) ? "asc" : "desc");
                i++;
            }
        }
        List<Object[]> list = super.sqlQueryByNativeSql(sbSql.toString(), params.toArray());
        return list;
    }
}
