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
 * 查询手动添加的订单明细
 * Created by Allen on 2015/8/5.
 */
@Service("findPlaceOrderTMListForManualByOrderIdDAO")
public class FindPlaceOrderTMListForManualByOrderIdDAOImpl extends BaseQueryDao implements FindListByWhereDAO {

    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT potm.id, potm.order_id, tm.id as tmId, tm.name, potm.tm_price, potm.count, potm.creator, potm.create_time, potm.course_code ");
        sql.append("FROM place_order_teach_material potm, teach_material tm ");
        sql.append("WHERE potm.teach_material_id = tm.id ");
        sql.append("AND potm.order_id = ? ");

        List<Object> params = new ArrayList<Object>();
        String orderId = paramsMap.get("orderId");
        params.add(orderId);
        if(!StringUtils.isEmpty(paramsMap.get("state"))){
            int state = Integer.parseInt(paramsMap.get("state"));
            if(state < 2){
                sql.append("AND tm.state = 0 ");
            }
            if(state > 3){
                sql.append("AND potm.is_send = 1 ");
            }
        }

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
