package com.zs.dao.placeorder.impl;

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
 * 统计已发出的预订单的教材
 * Created by Allen on 2015/9/16.
 */
@Service("countSendPlaceOrderTMDAO")
public class CountSendPlaceOrderTMDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String tmName = paramsMap.get("tmName");
        String spotCodes = paramsMap.get("spotCodes");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");
        String orderCode = paramsMap.get("orderCode");

        String field = "potm.teach_material_id, tm.name, tm.isbn, tm.author, potm.tm_price, sum(potm.count) count";
        StringBuilder sql = new StringBuilder("from teach_material tm, teach_material_place_order tmpo, place_order_teach_material potm, place_order_package pop ");
        sql.append("where tm.id = potm.teach_material_id and tmpo.package_id = pop.id and tmpo.id = potm.order_id and potm.is_send= 1 and tmpo.order_status > '3' and potm.count > 0 ");

        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and tmpo.semester_id = ? ");
            param.add(semesterId);
        }
        if(!StringUtils.isEmpty(orderCode)){
            sql.append("and tmpo.order_code = ? ");
            param.add(orderCode);
        }
        if(!StringUtils.isEmpty(tmName)){
            sql.append("and tm.name like ? ");
            param.add("%"+tmName+"%");
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
        sql.append("group by potm.teach_material_id, tm.name, tm.isbn, tm.author, potm.tm_price ");

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
