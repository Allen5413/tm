package com.zs.dao.placeorder.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/8/1.
 */
@Service("findPlaceOrderTMForSpotPrintDAO")
public class FindPlaceOrderTMForSpotPrintDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String state = paramsMap.get("state");
        String operateTime = paramsMap.get("operateTime");

        //订单明细课程关联教材的统计sql
        StringBuilder courseTMSql = new StringBuilder();
        courseTMSql.append("SELECT tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.order_code,potm.course_code,tm.name AS tmName,tm.author,tm.price,potm.count,tmpo.order_status,tm.price * potm.count as totalPrice,tmpo.address,tmpo.admin_name ");
        courseTMSql.append("FROM teach_material_place_order tmpo,place_order_teach_material potm,teach_material_course tmc,teach_material tm ");
        courseTMSql.append("WHERE tmpo.id = potm.order_id AND tmc.course_code = potm.course_code AND tmc.teach_material_id = tm.id AND tm.state = 0 AND tmpo.is_stock = 0 AND potm.count > 0 AND tmpo.is_stock = 0 ");
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
            courseTMSql.append("AND tmpo.spot_code = ? ");
            param.add(spotCode);
        }
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            courseTMSql.append("AND tmpo.operate_time = ? ");
            param.add(operateTime);
        }
        //订单明细课程关联套教材的统计sql
        StringBuilder courseSTMSql = new StringBuilder();
        courseSTMSql.append("SELECT tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.order_code,potm.course_code,tm.name AS tmName,tm.author,tm.price,potm.count,tmpo.order_status,tm.price * potm.count as totalPrice,tmpo.address,tmpo.admin_name ");
        courseSTMSql.append("FROM teach_material_place_order tmpo,place_order_teach_material potm,set_teach_material stm,set_teach_material_tm stmtm,teach_material tm ");
        courseSTMSql.append("WHERE tmpo.id = potm.order_id AND stm.buy_course_code = potm.course_code AND stm.id = stmtm.set_teach_material_id AND stmtm.teach_material_id = tm.id AND tm.state = 0 AND tmpo.is_stock = 0 AND potm.count > 0 AND tmpo.is_stock = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            courseSTMSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            courseSTMSql.append("AND tmpo.order_status = ? ");
            param.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            courseSTMSql.append("AND tmpo.spot_code = ? ");
            param.add(spotCode);
        }
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            courseSTMSql.append("AND tmpo.operate_time = ? ");
            param.add(operateTime);
        }
        // 订单明细直接存的教材的统计sql
        StringBuilder tmSql = new StringBuilder();
        tmSql.append("SELECT '' AS spec_code,'' AS level_code,'' AS en_year,'' AS en_quarter,tmpo.order_code,'' AS course_code,tm. NAME AS tmName,tm.author,potm.tm_price,potm.count,tmpo.order_status,tm.price * potm.count as totalPrice,tmpo.address,tmpo.admin_name ");
        tmSql.append("FROM teach_material_place_order tmpo,place_order_teach_material potm,teach_material tm ");
        tmSql.append("WHERE tmpo.id = potm.order_id AND potm.teach_material_id = tm.id AND tm.state = 0 AND tmpo.is_stock = 0 AND potm.count > 0 AND tmpo.is_stock = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            tmSql.append("AND tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            tmSql.append("AND tmpo.order_status = ? ");
            param.add(state);
        }
        if(!StringUtils.isEmpty(spotCode)){
            tmSql.append("AND tmpo.spot_code = ? ");
            param.add(spotCode);
        }
        if(state.equals(TeachMaterialPlaceOrder.STATE_SORTING)){
            tmSql.append("AND tmpo.operate_time = ? ");
            param.add(operateTime);
        }
        StringBuilder sql = new StringBuilder("SELECT * FROM (");
        sql.append(courseTMSql);
        sql.append(" UNION ALL ");
        sql.append(courseSTMSql);
        sql.append(" UNION ALL ");
        sql.append(tmSql);
        sql.append(") t ");


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
