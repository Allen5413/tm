package com.zs.dao.statis.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/11/25 0025.
 */
@Service("findIssueChannelPayDetailDAO")
public class FindIssueChannelPayDetailDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String icId = paramsMap.get("icId");
        String type = paramsMap.get("type");

        //统计学生订单的sql
        StringBuilder studentSql = new StringBuilder();
        studentSql.append("SELECT tm.name, tm.isbn, tm.author, sbotm.price, sum(sbotm.count) count ");
        studentSql.append("FROM student_book_order sbo, student_book_order_tm sbotm, teach_material tm ");
        studentSql.append("WHERE sbo.order_code = sbotm.order_code AND sbotm.teach_material_id = tm.id AND sbo.state > 3 AND sbotm.count > 0 AND sbotm.is_send = 1 ");
        studentSql.append("AND sbo.semester_id = ? AND sbo.issue_channel_id = ? ");
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(icId));
        if(!StringUtils.isEmpty(type)){
            studentSql.append("AND tm.teach_material_type_id = ? ");
            param.add(Integer.parseInt(type));
        }
        studentSql.append("GROUP BY tm.name, tm.isbn, tm.author, sbotm.price ");

        //统计预订单的sql
        StringBuilder spotSql = new StringBuilder();
        spotSql.append("SELECT tm.name, tm.isbn, tm.author, potm.tm_price, sum(potm.count) count ");
        spotSql.append("FROM teach_material_place_order tmpo, place_order_teach_material potm, teach_material tm, issue_range ir ");
        spotSql.append("WHERE tmpo.id = potm.order_id AND potm.teach_material_id = tm.id AND tmpo.spot_code = ir.spot_code AND tmpo.order_status > '3' AND potm.count > 0 AND potm.is_send = 1 ");
        spotSql.append("AND tmpo.semester_id = ? AND ir.issue_channel_id = ? ");
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(icId));
        if(!StringUtils.isEmpty(type)){
            spotSql.append("AND tm.teach_material_type_id = ? ");
            param.add(Integer.parseInt(type));
        }
        spotSql.append("GROUP BY tm.name, tm.isbn, tm.author, potm.tm_price ");

        //统计一次性订单的sql
        StringBuilder onceSql = new StringBuilder();
        onceSql.append("SELECT tm.name, tm.isbn, tm.author, sbotm.price, sum(sbotm.count) count ");
        onceSql.append("FROM student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm ");
        onceSql.append("WHERE sbo.id = sbotm.order_id AND sbotm.teach_material_id = tm.id AND sbo.state > 4 AND sbotm.count > 0 AND sbotm.is_send = 1 ");
        onceSql.append("AND sbo.semester_id = ? AND sbo.issue_channel_id = ? ");
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(icId));
        if(!StringUtils.isEmpty(type)){
            onceSql.append("AND tm.teach_material_type_id = ? ");
            param.add(Integer.parseInt(type));
        }
        onceSql.append("GROUP BY tm.name, tm.isbn, tm.author, sbotm.price ");

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select t.name, t.isbn, t.author, t.price, sum(t.count) count ");
        sql.append("from (");
        sql.append(studentSql);
        sql.append("UNION ALL ");
        sql.append(spotSql);
        sql.append("UNION ALL ");
        sql.append(onceSql);
        sql.append(") t ");
        sql.append("GROUP BY t.name, t.isbn, t.author, t.price");
        sql.append(") tt ");

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
