package com.zs.dao.placeorder.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/8/1.
 */
@Service("findPlaceOrderForSpotPrintDAO")
public class FindPlaceOrderForSpotPrintDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object> params = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String state = paramsMap.get("state");

        //预订单明细课程关联教材sql
        StringBuilder courseTmSql = new StringBuilder();
        courseTmSql.append("SELECT sp.code AS spotCode,sp.name AS spotName,tm.name AS tmName,tm.author,tm.price AS tmPrice,potm.course_code AS courseCode,sum(potm.count) AS count,sum(potm.count * tm.price) AS totalPrice ");
        courseTmSql.append("FROM sync_spot sp,teach_material_place_order tmpo,place_order_teach_material potm,teach_material_course tmc,teach_material tm ");
        courseTmSql.append("WHERE sp.code = tmpo.spot_code AND tmpo.id = potm.order_id AND potm.course_code = tmc.course_code AND tm.id = tmc.teach_material_id AND tm.state = 0 AND tmpo.is_stock = 0 AND potm.count > 0 AND tmpo.is_stock = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            courseTmSql.append("AND tmpo.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            courseTmSql.append("AND tmpo.order_status = ? ");
            params.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            courseTmSql.append("AND tmpo.spot_code = ? ");
            params.add(spotCode);
        }
        courseTmSql.append("GROUP BY sp.code,sp.name,tm.name,tm.author,tm.price,potm.course_code ");
        //预订单明细课程关联套教材sql
        StringBuilder courseSTmSql = new StringBuilder();
        courseSTmSql.append("SELECT sp.code AS spotCode,sp.name AS spotName,tm.name AS tmName,tm.author,tm.price AS tmPrice,potm.course_code AS courseCode,sum(potm.count) AS count,sum(potm.count * tm.price) AS totalPrice ");
        courseSTmSql.append("FROM sync_spot sp,teach_material_place_order tmpo,place_order_teach_material potm,set_teach_material stm,set_teach_material_tm stmtm,teach_material tm ");
        courseSTmSql.append("WHERE sp.code = tmpo.spot_code AND tmpo.id = potm.order_id AND potm.course_code = stm.buy_course_code AND stm.id = stmtm.set_teach_material_id AND tm.id = stmtm.teach_material_id AND tm.state = 0 AND tmpo.is_stock = 0 AND potm.count > 0 AND tmpo.is_stock = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            courseSTmSql.append("AND tmpo.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            courseSTmSql.append("AND tmpo.order_status = ? ");
            params.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            courseSTmSql.append("AND tmpo.spot_code = ? ");
            params.add(spotCode);
        }
        courseSTmSql.append("GROUP BY sp.code,sp.name,tm.name,tm.author,tm.price,potm.course_code ");
        //预订单明细教材的关联课程sql
        StringBuilder tmCourseSql = new StringBuilder();
        tmCourseSql.append("SELECT sp.code AS spotCode,sp.name AS spotName,tm.name AS tmName,tm.author,potm.tm_price AS tmPrice,tmc.course_code AS courseCode,sum(potm.count) AS count,sum(potm.count * potm.tm_price) AS totalPrice ");
        tmCourseSql.append("FROM sync_spot sp,teach_material_place_order tmpo,place_order_teach_material potm,teach_material tm,teach_material_course tmc ");
        tmCourseSql.append("WHERE sp.code = tmpo.spot_code AND tmpo.id = potm.order_id AND potm.teach_material_id = tm.id AND tm.id = tmc.teach_material_id AND tm.state = 0 AND tmpo.is_stock = 0 AND potm.count > 0 AND tmpo.is_stock = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            tmCourseSql.append("AND tmpo.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            tmCourseSql.append("AND tmpo.order_status = ? ");
            params.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            tmCourseSql.append("AND tmpo.spot_code = ? ");
            params.add(spotCode);
        }
        tmCourseSql.append("GROUP BY sp.code,sp.name,tm.name,tm.author,potm.tm_price,tmc.course_code ");
        //预订单明细套教材的关联课程sql
        StringBuilder stmCourseSql = new StringBuilder();
        stmCourseSql.append("SELECT sp.code AS spotCode,sp.name AS spotName,tm.name AS tmName,tm.author,potm.tm_price AS tmPrice,stm.buy_course_code AS courseCode,sum(potm.count) AS count,sum(potm.count * potm.tm_price) AS totalPrice ");
        stmCourseSql.append("FROM sync_spot sp,teach_material_place_order tmpo,place_order_teach_material potm,teach_material tm,set_teach_material stm,set_teach_material_tm stmtm ");
        stmCourseSql.append("WHERE sp.code = tmpo.spot_code AND tmpo.id = potm.order_id AND potm.teach_material_id = tm.id AND tm.id = stmtm.teach_material_id AND stmtm.set_teach_material_id = stm.id AND tm.state = 0 AND tmpo.is_stock = 0 AND potm.count > 0 AND tmpo.is_stock = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            stmCourseSql.append("AND tmpo.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            stmCourseSql.append("AND tmpo.order_status = ? ");
            params.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            stmCourseSql.append("AND tmpo.spot_code = ? ");
            params.add(spotCode);
        }
        stmCourseSql.append("GROUP BY sp.code,sp.name,tm.name,tm.author,potm.tm_price,stm.buy_course_code ");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CASE WHEN count(t2.deptName) = 1 THEN t2.deptName ELSE '公共' END AS deptName,t.* ");
        sql.append("FROM (");
        sql.append("SELECT total.spotCode, total.spotName, total.tmName, total.author, total.tmPrice, total.courseCode, sum(total.count) AS count, sum(total.totalPrice) AS totalPrice ");
        sql.append("FROM (");
        sql.append(courseTmSql);
        sql.append("UNION ALL ");
        sql.append(courseSTmSql);
        sql.append("UNION ALL ");
        sql.append(tmCourseSql);
        sql.append("UNION ALL ");
        sql.append(stmCourseSql);
        sql.append(") total ");
        sql.append("GROUP BY total.spotCode, total.spotName, total.tmName, total.author, total.tmPrice, total.courseCode ");
        sql.append(") t ");
        sql.append("LEFT JOIN (");
        sql.append("SELECT dept.name AS deptName,bs.course_code ");
        sql.append("FROM semester s,sync_begin_schedule bs,sync_spec spec,sync_department dept ");
        sql.append("WHERE s.year = bs.academic_year AND s.quarter = bs.term AND bs.spec_code = spec.code AND spec.dept_code = dept.code ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("AND s.id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        sql.append("GROUP BY dept.name,bs.course_code");
        sql.append(") t2 ON t.courseCode = t2.course_code ");
        sql.append("GROUP BY tmName,author,tmPrice,count,totalPrice ");

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
