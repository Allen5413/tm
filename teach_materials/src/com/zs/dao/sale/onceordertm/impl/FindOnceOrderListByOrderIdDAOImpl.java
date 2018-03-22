package com.zs.dao.sale.onceordertm.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/26.
 */
@Service("findOnceOrderListByOrderIdDAO")
public class FindOnceOrderListByOrderIdDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder();

        sql.append("select sbotm.id, sc.code as courseCode, sc.name as courseName, tm.name, sbotm.price, sbotm.count, sbotm.operator, sbotm.operate_time, sbo.package_id, sbotm.is_must, sbotm.is_buy, sbotm.xf, sbotm.is_select from ");
        sql.append("student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm, sync_course sc where ");
        sql.append("(CASE WHEN sbo.state < 3 THEN tm.state = 0 WHEN sbo.state >= 5 THEN sbotm.is_send = 1 ELSE 1=1 END) and sbo.id = sbotm.order_id and sbotm.teach_material_id = tm.id and sbotm.course_code = sc.code and sbo.id = ? ");

        List<Object> params = new ArrayList<Object>();
        String orderId = paramsMap.get("orderId");
        params.add(orderId);
        if(null != sortMap) {
            sql.append("order by ");
            int i = 0;
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                if(0 < i){
                    sql.append(",");
                }
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
                i++;
            }
        }
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), params.toArray());
        return list;
    }
}
