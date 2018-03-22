package com.zs.dao.placeorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 统计学习中心的教材总数和总价
 * Created by Allen on 2015/7/31.
 */
@Service("findPlaceOrderForSpotCountDAO")
public class FindPlaceOrderForSpotCountDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String state = paramsMap.get("state");
        String spotCode = paramsMap.get("spotCode");

        //订单明细课程关联教材的统计sql
        StringBuilder courseTMSql = new StringBuilder("");
        courseTMSql.append("SELECT sp.code, sp.name, ");
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            courseTMSql.append("tmpo.operator, tmpo.operate_time, ");
        }
        courseTMSql.append("sum(potm.count) AS totalCount, sum(potm.count * tm.price) AS totalPrice ");
        courseTMSql.append("FROM sync_spot sp, teach_material_place_order tmpo, place_order_teach_material potm, teach_material_course tmc, teach_material tm ");
        courseTMSql.append("WHERE sp.code = tmpo.spot_code AND tmpo.id = potm.order_id AND potm.course_code = tmc.course_code AND tmc.teach_material_id = tm.id AND tmpo.is_stock = 0 AND potm.count > 0 AND tm.state = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            courseTMSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            if("1".equals(state)){
                courseTMSql.append("AND (CASE WHEN tmpo.is_spec_all = 0 THEN tmpo.order_status = '1' ELSE tmpo.order_status = '2' END)");
            }else {
                courseTMSql.append("AND tmpo.order_status = ? ");
                param.add(state);
            }
        }
        if(!StringUtils.isEmpty(spotCode)){
            courseTMSql.append("AND sp.code = ? ");
            param.add(spotCode);
        }
        courseTMSql.append("GROUP BY sp.code, sp.name ");
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            courseTMSql.append(", tmpo.operator, tmpo.operate_time ");
        }
        //订单明细课程关联套教材的统计sql
        StringBuilder courseSTMSql = new StringBuilder("");
        courseSTMSql.append("SELECT sp.code, sp.name, ");
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            courseSTMSql.append("tmpo.operator, tmpo.operate_time, ");
        }
        courseSTMSql.append("sum(potm.count) AS totalCount, sum(potm.count * tm.price) AS totalPrice ");
        courseSTMSql.append("FROM sync_spot sp, teach_material_place_order tmpo, place_order_teach_material potm, set_teach_material stm, set_teach_material_tm stmtm, teach_material tm ");
        courseSTMSql.append("WHERE sp.code = tmpo.spot_code AND tmpo.id = potm.order_id AND potm.course_code = stm.buy_course_code AND stm.id = stmtm.set_teach_material_id AND stmtm.teach_material_id = tm.id AND tmpo.is_stock = 0 AND potm.count > 0 AND tm.state = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            courseSTMSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            courseSTMSql.append("AND tmpo.order_status = ? ");
            param.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            courseSTMSql.append("AND sp.code = ? ");
            param.add(spotCode);
        }
        courseSTMSql.append("GROUP BY sp.code, sp.name ");
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            courseSTMSql.append(", tmpo.operator, tmpo.operate_time ");
        }
        // 订单明细直接存的教材的统计sql
        StringBuilder tmSql = new StringBuilder("");
        tmSql.append("SELECT sp.code, sp.name, ");
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            tmSql.append("tmpo.operator, tmpo.operate_time, ");
        }
        tmSql.append("sum(potm.count) AS totalCount, sum(potm.count * potm.tm_price) AS totalPrice ");
        tmSql.append("FROM sync_spot sp, teach_material_place_order tmpo, place_order_teach_material potm, teach_material tm ");
        tmSql.append("WHERE sp.code = tmpo.spot_code AND tmpo.id = potm.order_id AND potm.teach_material_id = tm.id AND tmpo.is_stock = 0 AND potm.count > 0 AND tm.state = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            tmSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            tmSql.append("AND tmpo.order_status = ? ");
            param.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            tmSql.append("AND sp.code = ? ");
            param.add(spotCode);
        }
        tmSql.append("GROUP BY sp.code, sp.name ");
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            tmSql.append(", tmpo.operator, tmpo.operate_time ");
        }

        String field = "t.code, t.name, ";
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            field += "t.operator, t.operate_time, ";
        }
        field += "sum(t.totalCount), sum(t.totalPrice)";
        StringBuilder sql = new StringBuilder("FROM (");
        sql.append(courseTMSql);
        sql.append(" UNION ALL ");
        sql.append(courseSTMSql);
        sql.append(" UNION ALL ");
        sql.append(tmSql);
        sql.append(") t ");
        sql.append("GROUP BY t.code,t.name ");
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            sql.append(", t.operator, t.operate_time ");
        }
        if(null != sortMap) {
            sql.append(" ORDER BY ");
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
