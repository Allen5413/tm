package com.zs.dao.orderbook.purchaseorderteachmaterial.impl;

import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.BaseQueryDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 订单入库时，通过订单编号，查询教材信息
 * Created by Allen on 2015/5/12.
 */
@Service("findPurchaseOrderTeachMaterialListByOrderCodeDAO")
public class FindPurchaseOrderTeachMaterialListByOrderCodeDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sbSql = new StringBuilder();

        sbSql.append("select case when count(t2.name) = 1 then t2.name else '公共' end as deptName, t.* from ");
        sbSql.append("(select potm.id, potm.course_code, potm.state, p.name as pressName, tm.isbn, tm.name as tmName, tm.author, tm.price, ifnull(potm.teach_material_count, 0) as tmCount, ifnull(potm.put_storage_count, 0) as storageCount, potm.operator, potm.operate_time from purchase_order_teach_material potm, teach_material tm, press p where potm.teach_material_id = tm.id and tm.press_id = p.id and potm.code = ?) t ");
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
