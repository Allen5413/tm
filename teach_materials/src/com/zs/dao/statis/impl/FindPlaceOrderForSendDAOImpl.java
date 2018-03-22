package com.zs.dao.statis.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 统计一个学期订单发出情况
 * Created by Allen on 2015/9/23.
 */
@Service("findPlaceOrderForSendDAO")
public class FindPlaceOrderForSendDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");

        //统计学生订单的sql
        StringBuilder studentSql = new StringBuilder();
        studentSql.append("select tt.date, tt.count orderCount, tt2.count packageCount, tt3.price from(");
        studentSql.append("select DATE(sbop.send_time) date, count(sbo.id) count ");
        studentSql.append("from student_book_order sbo, student_book_order_package sbop ");
        studentSql.append("where sbo.package_id = sbop.id and sbo.semester_id = ? and sbop.send_time is not null ");
        studentSql.append("group by date");
        studentSql.append(") tt, (");
        studentSql.append("select ttt.date, count(ttt.id) count from (");
        studentSql.append("select DATE(sbop.send_time) date, sbop.id ");
        studentSql.append("from student_book_order sbo, student_book_order_package sbop ");
        studentSql.append("where sbo.package_id = sbop.id and sbo.semester_id = ? and sbop.send_time is not null ");
        studentSql.append("group by date, sbop.id) ttt GROUP BY ttt.date");
        studentSql.append(") tt2, (");
        studentSql.append("select date(sbop.send_time) date, sum(sbotm.count * sbotm.price) price ");
        studentSql.append("from student_book_order_package sbop, student_book_order sbo, student_book_order_tm sbotm ");
        studentSql.append("where sbop.id = sbo.package_id and sbo.semester_id = ? and sbo.order_code= sbotm.order_code and sbop.send_time is not null ");
        studentSql.append("GROUP BY date) tt3 where tt.date = tt2.date and tt2.date = tt3.date ");

        //统计预订单的sql
        StringBuilder placeSql = new StringBuilder();
        placeSql.append("select tt4.date, tt4.count orderCount, tt5.count packageCount, tt6.price from (");
        placeSql.append("select DATE(pop.send_time) date, count(tmpo.id) count ");
        placeSql.append("from teach_material_place_order tmpo, place_order_package pop ");
        placeSql.append("where tmpo.package_id = pop.id and tmpo.semester_id = ? and pop.send_time is not null ");
        placeSql.append("group by date");
        placeSql.append(") tt4,(");
        placeSql.append("select ttt2.date, count(ttt2.id) count from (");
        placeSql.append("select DATE(pop.send_time) date, pop.id ");
        placeSql.append("from teach_material_place_order tmpo, place_order_package pop ");
        placeSql.append("where tmpo.package_id = pop.id and tmpo.semester_id = ? and pop.send_time is not null ");
        placeSql.append("group by date, pop.id) ttt2 GROUP BY ttt2.date");
        placeSql.append(") tt5, (");
        placeSql.append("select date(pop.send_time) date, sum(potm.count * potm.tm_price) price ");
        placeSql.append("from place_order_package pop, teach_material_place_order tmpo, place_order_teach_material potm ");
        placeSql.append("where pop.id = tmpo.package_id and tmpo.semester_id = ? and tmpo.id = potm.order_id and pop.send_time is not null ");
        placeSql.append("GROUP BY date) tt6 where tt4.date = tt5.date and tt5.date = tt6.date ");

        //统计一次性订单的sql
        StringBuilder onceSql = new StringBuilder();
        onceSql.append("select tt7.date, tt7.count orderCount, tt8.count packageCount, tt9.price from(");
        onceSql.append("select DATE(sbop.send_time) date, count(sbo.id) count ");
        onceSql.append("from student_book_once_order sbo, student_book_order_package sbop ");
        onceSql.append("where sbo.package_id = sbop.id and sbo.semester_id = ? and sbop.send_time is not null ");
        onceSql.append("group by date");
        onceSql.append(") tt7, (");
        onceSql.append("select ttt3.date, count(ttt3.id) count from (");
        onceSql.append("select DATE(sbop.send_time) date, sbop.id ");
        onceSql.append("from student_book_once_order sbo, student_book_order_package sbop ");
        onceSql.append("where sbo.package_id = sbop.id and sbo.semester_id = ? and sbop.send_time is not null ");
        onceSql.append("group by date, sbop.id) ttt3 GROUP BY ttt3.date");
        onceSql.append(") tt8, (");
        onceSql.append("select date(sbop.send_time) date, sum(sbotm.count * sbotm.price) price ");
        onceSql.append("from student_book_order_package sbop, student_book_once_order sbo, student_book_once_order_tm sbotm ");
        onceSql.append("where sbop.id = sbo.package_id and sbo.semester_id = ? and sbo.id = sbotm.order_id and sbop.send_time is not null ");
        onceSql.append("GROUP BY date) tt9 where tt7.date = tt8.date and tt8.date = tt9.date ");

        StringBuilder sql = new StringBuilder();
        sql.append("select t.date, sum(t.orderCount), sum(t.packageCount), sum(t.price) from (");
        sql.append(studentSql);
        sql.append("union all ");
        sql.append(placeSql);
        sql.append("union all ");
        sql.append(onceSql);
        sql.append(") t GROUP BY t.date order by t.date");

        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));
        param.add(Long.parseLong(semesterId));

        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
