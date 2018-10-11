package com.zs.dao.placeorder.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;

@Service("findPlaceOrderPageByWhereDAO")
public class FindPlaceOrderPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO{
	
	@Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo placeOrderPageInfo = new PageInfo();
        placeOrderPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        placeOrderPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        String tmCount = paramsMap.get("tmCount");

        List<Object> param = new ArrayList<Object>();

        //统计课程关联教材sql
        StringBuilder courseTmSql = new StringBuilder();
        courseTmSql.append("SELECT tmpo.id,tmpo.order_code,sp.code spot_code,sp.name,tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.operator,tmpo.operate_time,tmpo.order_status,tmpo.address,tmpo.admin_name,tmpo.phone,tmpo.tel,tmpo.postal_code,sum(potm.count) tmCount,sum(tm.price * potm.count) orderPrice ");
        courseTmSql.append("FROM teach_material_place_order tmpo INNER JOIN sync_spot sp ON tmpo.spot_code = sp.`code` INNER JOIN place_order_teach_material potm ON tmpo.id = potm.order_id INNER JOIN teach_material_course tmc ON potm.course_code = tmc.course_code INNER JOIN teach_material tm ON tmc.teach_material_id = tm.id ");
        courseTmSql.append("WHERE order_status < '5' AND (CASE WHEN tmpo.order_status < '4' THEN tm.state = 0 WHEN tmpo.order_status >= '5' THEN potm.is_send = 1 ELSE 1=1 END) ");
        this.assembleWhere(paramsMap, courseTmSql, param);
        courseTmSql.append("GROUP BY tmpo.id,tmpo.order_code,tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.operator,tmpo.operate_time,tmpo.order_status,tmpo.address,tmpo.admin_name,tmpo.phone,tmpo.tel,tmpo.postal_code ");

        //统计课程关联套教材sql
        StringBuilder courseStmSql = new StringBuilder();
        courseStmSql.append("SELECT tmpo.id,tmpo.order_code,sp.code spot_code,sp.name,tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.operator,tmpo.operate_time,tmpo.order_status,tmpo.address,tmpo.admin_name,tmpo.phone,tmpo.tel,tmpo.postal_code,sum(potm.count) tmCount,sum(tm.price * potm.count) orderPrice ");
        courseStmSql.append("FROM teach_material_place_order tmpo INNER JOIN sync_spot sp ON tmpo.spot_code = sp.`code` INNER JOIN place_order_teach_material potm ON tmpo.id = potm.order_id INNER JOIN set_teach_material stm ON potm.course_code = stm.buy_course_code INNER JOIN set_teach_material_tm stmtm ON stm.id = stmtm.set_teach_material_id INNER JOIN teach_material tm ON stmtm.teach_material_id = tm.id ");
        courseStmSql.append("WHERE (CASE WHEN tmpo.order_status < '4' THEN tm.state = 0 WHEN tmpo.order_status >= '5' THEN potm.is_send = 1 ELSE 1=1 END) ");
        this.assembleWhere(paramsMap, courseStmSql, param);
        courseStmSql.append("GROUP BY tmpo.id,tmpo.order_code,tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.operator,tmpo.operate_time,tmpo.order_status,tmpo.address,tmpo.admin_name,tmpo.phone,tmpo.tel,tmpo.postal_code ");

        //统计教材关联sql 手动添加的订单
        StringBuilder tmSql = new StringBuilder();
        tmSql.append("SELECT tmpo.id,tmpo.order_code,sp.code spot_code,sp.name,tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.operator,tmpo.operate_time,tmpo.order_status,tmpo.address,tmpo.admin_name,tmpo.phone,tmpo.tel,tmpo.postal_code,sum(potm.count) tmCount,sum(potm.tm_price * potm.count) orderPrice ");
        tmSql.append("FROM teach_material_place_order tmpo INNER JOIN sync_spot sp ON tmpo.spot_code = sp.`code` INNER JOIN place_order_teach_material potm ON tmpo.id = potm.order_id INNER JOIN teach_material tm ON potm.teach_material_id = tm.id ");
        tmSql.append("WHERE (CASE WHEN tmpo.order_status < '4' THEN tm.state = 0 WHEN tmpo.order_status >= '5' THEN potm.is_send = 1 ELSE 1=1 END) ");
        this.assembleWhere(paramsMap, tmSql, param);
        tmSql.append("GROUP BY tmpo.id,tmpo.order_code,tmpo.spec_code,tmpo.level_code,tmpo.en_year,tmpo.en_quarter,tmpo.operator,tmpo.operate_time,tmpo.order_status,tmpo.address,tmpo.admin_name,tmpo.phone,tmpo.tel,tmpo.postal_code ");


        String field = "tt.*";

        StringBuilder sql = new StringBuilder("FROM (SELECT t.id,t.spot_code,t.name spotName,t.order_code,t.spec_code,t.level_code,t.en_year,t.en_quarter,t.operator,t.operate_time,t.order_status,t.address,t.admin_name,t.phone,t.tel,t.postal_code,sum(tmCount) tmCount,sum(orderPrice) orderPrice FROM (");
        sql.append(courseTmSql);
        sql.append("UNION ALL ");
        sql.append(courseStmSql);
        sql.append("UNION ALL ");
        sql.append(tmSql);
        sql.append(") t ");
        sql.append("WHERE 1=1 ");

        if("0".equals(tmCount)){
            sql.append("AND t.tmCount > 0 ");
        }

        sql.append("GROUP BY id,spot_code,spotName,order_code,spec_code,level_code,en_year,en_quarter,operator,operate_time,order_status,address,admin_name,phone,tel,postal_code ");

        if(null != sortMap) {
            sql.append(" order by ");
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
            }
        }
        sql.append(") tt");
        placeOrderPageInfo = super.pageSqlQueryByNativeSql(placeOrderPageInfo, sql.toString(), field, param.toArray());
        return placeOrderPageInfo;
    }


    private void assembleWhere(Map<String, String> paramsMap, StringBuilder sql, List<Object> param){
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String orderCode = paramsMap.get("orderCode");
        String state = paramsMap.get("state");
        String isStock = paramsMap.get("isStock");
        String enterYear = paramsMap.get("enterYear");
        String quarter = paramsMap.get("quarter");
        String packageId = paramsMap.get("packageId");
        String tmId = paramsMap.get("tmId");
        String isSpecAll = paramsMap.get("isSpecAll");

        if(!StringUtils.isEmpty(semesterId)){
            sql.append(" and tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and tmpo.spot_code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(specCode)){
            sql.append(" and tmpo.spec_code = ? ");
            param.add(specCode);
        }
        if(!StringUtils.isEmpty(levelCode)){
            sql.append(" and tmpo.level_code = ? ");
            param.add(levelCode);
        }
        if(!StringUtils.isEmpty(enterYear)){
            sql.append(" and tmpo.en_year = ? ");
            param.add(Integer.parseInt(enterYear));
        }
        if(!StringUtils.isEmpty(quarter)){
            sql.append(" and tmpo.en_quarter = ? ");
            param.add(Integer.parseInt(quarter));
        }
        if(!StringUtils.isEmpty(orderCode)){
            sql.append(" and tmpo.order_code = ? ");
            param.add(orderCode);
        }
        if(!StringUtils.isEmpty(state)){
            sql.append(" and tmpo.order_status = ? ");
            param.add(Integer.parseInt(state));
        }
        if(!StringUtils.isEmpty(isStock)){
            sql.append(" and tmpo.is_stock = ? ");
            param.add(Integer.parseInt(isStock));
        }
        if(!StringUtils.isEmpty(packageId)){
            sql.append(" and tmpo.package_id = ? ");
            param.add(Long.parseLong(packageId));
        }
        if(!StringUtils.isEmpty(tmId)){
            sql.append(" and tm.id = ? ");
            param.add(Long.parseLong(tmId));
        }
        if(!StringUtils.isEmpty(isSpecAll)){
            sql.append(" and tmpo.is_spec_all = ? ");
            param.add(Integer.parseInt(isSpecAll));
        }
    }
}
