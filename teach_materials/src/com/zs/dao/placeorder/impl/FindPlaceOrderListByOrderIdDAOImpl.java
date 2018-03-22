package com.zs.dao.placeorder.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.springframework.stereotype.Service;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
@Service("findPlaceOrderListByOrderIdDAOImpl")
public class FindPlaceOrderListByOrderIdDAOImpl extends BaseQueryDao implements FindListByWhereDAO{
	
	 @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

         List<Object> params = new ArrayList<Object>();
         String orderId = paramsMap.get("orderId");
         String state = paramsMap.get("state");
         String isGLTM = paramsMap.get("isGLTM");
         params.add(orderId);
         params.add(orderId);

        StringBuilder sql = new StringBuilder();

        sql.append("select t.* FROM ");
        sql.append("(SELECT potm.id, potm.order_id, c.code AS courseCode, c.name AS courseName, tm.id AS tmId, tm.name AS tmName, tm.price, potm.count, potm.creator, potm.create_time ");
        sql.append("FROM place_order_teach_material potm, teach_material tm, teach_material_course tmc, sync_course c ");
        sql.append("WHERE potm.course_code = tmc.course_code AND tmc.teach_material_id = tm.id AND tmc.course_code = c.code ");
        if((TeachMaterialPlaceOrder.STATE_SEND.equals(state) || TeachMaterialPlaceOrder.STATE_SIGN.equals(state)) && "0".equals(isGLTM)){
            sql.append("AND potm.teach_material_id = tm.id ");
        }
        if(Integer.parseInt(state) < Integer.parseInt(TeachMaterialPlaceOrder.STATE_SORTING)){
            sql.append("AND tm.state = 0 ");
        }
        sql.append("AND tm.state = 0 AND potm.order_id = ? ");
        sql.append("UNION ALL ");
        sql.append("SELECT potm.id, potm.order_id, c.code AS courseCode, c.name AS courseName, tm.id AS tmId, tm.name AS tmName, tm.price, potm.count, potm.creator, potm.create_time ");
        sql.append("FROM place_order_teach_material potm, teach_material tm, set_teach_material stm, set_teach_material_tm stmtm, sync_course c ");
        sql.append("WHERE potm.course_code = stm.buy_course_code AND stm.id = stmtm.set_teach_material_id AND stmtm.teach_material_id = tm.id ");
        if((TeachMaterialPlaceOrder.STATE_SEND.equals(state) || TeachMaterialPlaceOrder.STATE_SIGN.equals(state)) && "0".equals(isGLTM)){
            sql.append("AND potm.teach_material_id = tm.id ");
        }
        if(Integer.parseInt(state) < Integer.parseInt(TeachMaterialPlaceOrder.STATE_SORTING)){
            sql.append("AND tm.state = 0 ");
        }
        sql.append("AND stm.buy_course_code = c.`code` AND potm.order_id = ?) t ");


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
