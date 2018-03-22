package com.zs.dao.sale.studentbookordertm.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *查询库存不足的订单需要的教材数量
 * Created by Allen on 2015/8/10.
 */
@Service("findStudentBookOrderTMCountForNotStockDAO")
public class FindStudentBookOrderTMCountForNotStockDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder("SELECT sbo.id,sbo.issue_channel_id,sbotm.id sbotmId,tm.id tmId,sbotm.count ");
        sql.append("FROM student_book_order sbo, student_book_order_tm sbotm, teach_material tm, sync_student s ");
        sql.append("WHERE sbo.order_code = sbotm.order_code AND sbotm.teach_material_id = tm.id AND tm.state = 0 AND sbo.is_stock = 1 AND sbo.state < 2 AND sbo.student_code = s.code ");

        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");

        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("AND sbo.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("AND s.spot_code = ? ");
            params.add(spotCode);
        }
        if(null != sortMap) {
            sql.append("ORDER BY ");
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
