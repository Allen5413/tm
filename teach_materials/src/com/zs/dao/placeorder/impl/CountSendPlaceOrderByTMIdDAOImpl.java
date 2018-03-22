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
 * Created by Allen on 2015/9/16.
 */
@Service("countSendPlaceOrderByTMIdDAO")
public class CountSendPlaceOrderByTMIdDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String tmId = paramsMap.get("tmId");
        String spotCodes = paramsMap.get("spotCodes");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");
        String price = paramsMap.get("price");
        String orderCode = paramsMap.get("orderCode");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT tmpo.order_code, tmpo.spot_code, sp.name spotName, potm.tm_price, potm.count, potm.id ");
        sql.append("FROM teach_material_place_order tmpo, place_order_teach_material potm, sync_spot sp, place_order_package pop ");
        sql.append("WHERE tmpo.id = potm.order_id AND tmpo.package_id = pop.id AND sp.code = tmpo.spot_code AND tmpo.order_status > '3' AND potm.count > 0 ");

        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and tmpo.semester_id = ? ");
            param.add(semesterId);
        }
        if(!StringUtils.isEmpty(orderCode)){
            sql.append("and tmpo.order_code = ? ");
            param.add(orderCode);
        }
        if(!StringUtils.isEmpty(tmId)){
            sql.append("and potm.teach_material_id = ? ");
            param.add(Long.parseLong(tmId));
        }
        if(!StringUtils.isEmpty(price)){
            sql.append("and potm.tm_price = ? ");
            param.add(Float.parseFloat(price));
        }
        if(!StringUtils.isEmpty(spotCodes)){
            String[] spotCodeArray = spotCodes.split(",");
            if(null != spotCodeArray && 0 < spotCodeArray.length){
                sql.append("and tmpo.spot_code in (");
                int i = 0;
                for (String spotCode : spotCodeArray){
                    if(i == spotCodeArray.length - 1){
                        sql.append("?");
                    }else {
                        sql.append("?, ");
                    }
                    param.add(spotCode);
                    i++;
                }
                sql.append(") ");
            }
        }
        if(!StringUtils.isEmpty(beginDate)){
            sql.append("and pop.send_time >= ? ");
            param.add(beginDate+" 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)){
            sql.append("and pop.send_time <= ? ");
            param.add(endDate+" 23:59:59");
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
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
