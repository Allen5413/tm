package com.zs.dao.basic.teachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 统计一个学期库存不足的教材
 * Created by Allen on 2015/8/20.
 */
@Service("findTeachmaterialNotStockPageDAO")
public class FindTeachMaterialNotStockPageDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        String semesterId = paramsMap.get("semesterId");
        List<Object> param = new ArrayList<Object>();

        //学生订单教材统计sql
        StringBuilder studentOrderSql = new StringBuilder();
        studentOrderSql.append("SELECT tm.id, tm.name, tm.isbn, tm.author, tm.price, ic.id icId, ic.`name` icName, sum(sbotm.count) count, p.name pName ");
        studentOrderSql.append("FROM student_book_order sbo, student_book_order_tm sbotm, teach_material tm, sync_student s, issue_range ir, issue_channel ic, press p ");
        studentOrderSql.append("WHERE sbo.order_code = sbotm.order_code AND sbotm.teach_material_id = tm.id AND sbo.student_code = s.`code` AND s.spot_code = ir.spot_code AND ir.issue_channel_id = ic.id AND tm.press_id = p.id AND tm.state = 0 AND sbo.state BETWEEN 1 and 3 and sbo.is_stock = 1 ");
        if(!StringUtils.isEmpty(semesterId)){
            studentOrderSql.append("AND sbo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        studentOrderSql.append("GROUP BY tm.id, tm.name, tm.isbn, tm.author, icId, icName, pName ");

        //预订单课程关联教材统计sql
        StringBuilder placeOrderCourseTmSql = new StringBuilder();
        placeOrderCourseTmSql.append("SELECT tm.id, tm.name, tm.isbn, tm.author, tm.price, ic.id icId, ic.`name` icName, sum(potm.count) count, p.name pName ");
        placeOrderCourseTmSql.append("FROM teach_material_place_order tmpo, place_order_teach_material potm, teach_material_course tmc, teach_material tm, issue_range ir, issue_channel ic, press p ");
        placeOrderCourseTmSql.append("WHERE tmpo.id = potm.order_id AND potm.course_code = tmc.course_code AND tmc.teach_material_id = tm.id AND tmpo.spot_code = ir.spot_code AND ir.issue_channel_id = ic.id AND tm.press_id = p.id AND tm.state = 0 AND tmpo.order_status BETWEEN 1 and 3 and tmpo.is_spec_all = 0 and tmpo.is_stock = 1 ");
        if(!StringUtils.isEmpty(semesterId)){
            placeOrderCourseTmSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        placeOrderCourseTmSql.append("GROUP BY tm.id, tm.name, tm.isbn, tm.author, icId, icName, pName ");

        //预订单课程关联套教材统计sql
        StringBuilder placeOrderCourseSTmSql = new StringBuilder();
        placeOrderCourseSTmSql.append("SELECT tm.id, tm.name, tm.isbn, tm.author, tm.price, ic.id icId, ic.`name` icName, sum(potm.count) count, p.name pName ");
        placeOrderCourseSTmSql.append("FROM teach_material_place_order tmpo, place_order_teach_material potm, set_teach_material stm, set_teach_material_tm stmtm, teach_material tm, issue_range ir, issue_channel ic, press p ");
        placeOrderCourseSTmSql.append("WHERE tmpo.id = potm.order_id AND potm.course_code = stm.buy_course_code AND stm.id = stmtm.set_teach_material_id AND stmtm.teach_material_id = tm.id AND tmpo.spot_code = ir.spot_code AND ir.issue_channel_id = ic.id AND tm.press_id = p.id AND tm.state = 0 AND tmpo.order_status BETWEEN 1 and 3 and tmpo.is_spec_all = 0 and tmpo.is_stock = 1 ");
        if(!StringUtils.isEmpty(semesterId)){
            placeOrderCourseSTmSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        placeOrderCourseSTmSql.append("GROUP BY tm.id, tm.name, tm.isbn, tm.author, icId, icName, pName ");

        //预订单教材关联统计sql
        StringBuilder placeOrderTmSql = new StringBuilder();
        placeOrderTmSql.append("SELECT tm.id, tm.name, tm.isbn, tm.author, tm.price, ic.id icId, ic.`name` icName, sum(potm.count) count, p.name pName ");
        placeOrderTmSql.append("FROM teach_material_place_order tmpo, place_order_teach_material potm, teach_material tm, issue_range ir, issue_channel ic, press p ");
        placeOrderTmSql.append("WHERE tmpo.id = potm.order_id AND potm.teach_material_id = tm.id AND ir.spot_code = tmpo.spot_code AND ir.issue_channel_id = ic.id AND tm.press_id = p.id AND tm.state = 0 AND tmpo.order_status BETWEEN 1 and 3 and tmpo.is_spec_all = 0 and tmpo.is_stock = 1 ");
        if(!StringUtils.isEmpty(semesterId)){
            placeOrderTmSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        placeOrderTmSql.append("GROUP BY tm.id, tm.name, tm.isbn, tm.author, icId, icName, pName ");

        String field = "tttt.*";

        StringBuilder sql = new StringBuilder();
        sql.append("FROM (");
        sql.append("SELECT ttt.*, ttt.count-ttt.stock as stockD FROM (");
        sql.append("SELECT tt.*, IFNULL(tms.stock, 0) stock FROM (");
        sql.append("SELECT t.id, t.name, t.isbn, t.author, t.price, t.icId, t.icName, sum(t.count) count, t.pName FROM (");
        sql.append(studentOrderSql);
        sql.append("UNION ALL ");
        sql.append(placeOrderCourseTmSql);
        sql.append("UNION ALL ");
        sql.append(placeOrderCourseSTmSql);
        sql.append("UNION ALL ");
        sql.append(placeOrderTmSql);
        sql.append(")t GROUP BY t.id,t. NAME,t.isbn,t.author,t.price,t.icId,t.icName,t.pName ");
        sql.append(") tt ");
        sql.append("LEFT JOIN teach_material_stock tms ON tt.id = tms.teach_material_id AND tt.icId = tms.issue_channel_id ");
        sql.append(") ttt where ttt.stock < ttt.count ");
        sql.append(") tttt ");

        if(null != sortMap) {
            sql.append(" order by ");
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
            }
        }

        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
