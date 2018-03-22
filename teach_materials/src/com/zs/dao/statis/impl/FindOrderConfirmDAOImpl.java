package com.zs.dao.statis.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 统计一个学期的订单确认情况
 * Created by Allen on 2015/9/6.
 */
@Service("findOrderConfirmDAO")
public class FindOrderConfirmDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");

        //统计学生订单的sql
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select date(sbol.operate_time) date, count(*) confirmCount ");
        sql.append("from student_book_order sbo, student_book_order_log sbol where sbo.semester_id = ? ");
        sql.append("and sbo.order_code = sbol.order_code and sbol.state = 1 and sbo.state > 0 group by date ");
        sql.append("union all ");
        sql.append("select date(sbol.operate_time) date, count(*) confirmCount ");
        sql.append("from student_book_once_order sbo, student_book_once_order_log sbol where sbo.semester_id = ? ");
        sql.append("and sbo.id = sbol.order_id and sbol.state = 1 and sbo.state > 0 group by date ) t ");
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
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
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
