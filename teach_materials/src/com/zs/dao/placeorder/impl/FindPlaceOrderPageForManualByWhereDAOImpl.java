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
 * Created by Allen on 2015/8/4.
 */
@Service("findPlaceOrderPageForManualByWhere")
public class FindPlaceOrderPageForManualByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo placeOrderPageInfo = new PageInfo();
        placeOrderPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        placeOrderPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String provCode = paramsMap.get("provCode");
        String spotCode = paramsMap.get("spotCode");
        String orderCode = paramsMap.get("orderCode");
        String state = paramsMap.get("state");
        String isStock = paramsMap.get("isStock");
        String packageId = paramsMap.get("packageId");

        param.add(Long.parseLong(semesterId));


        String field = "spot.name as spotName, spec.name as specName, level.name as levelName, t.*";
        StringBuilder sql = new StringBuilder("from (select tmpo.id, tmpo.order_code, syncspot.code as spot_code, syncspot.name,tmpo.spec_code, tmpo.level_code, tmpo.en_year, tmpo.en_quarter, tmpo.operator, tmpo.operate_time, tmpo.order_status, sum(potm.count), sum(potm.count * potm.tm_price) ");
        sql.append("from teach_material_place_order tmpo, place_order_teach_material potm, sync_spot syncspot where tmpo.id = potm.order_id and tmpo.spot_code = syncspot.code AND tmpo.en_year IS NULL AND tmpo.en_quarter IS NULL AND tmpo.spec_code IS NULL AND tmpo.level_code IS NULL and tmpo.semester_id = ? ");
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and tmpo.spot_code = ? ");
            param.add(spotCode);
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
        sql.append("group by tmpo.order_code order by tmpo.operate_time desc) t ");
        //sql.append("left join sync_spot spot on t.spot_code = spot.code left join sync_spot_province sp on t.spot_code = sp.spot_code left join sync_province p on sp.province_code = p.code left join sync_spec spec on t.spec_code = spec.code left join sync_level level on t.level_code = level.code where 1=1 ");
        sql.append("left join sync_spot spot on t.spot_code = spot.code left join sync_spec spec on t.spec_code = spec.code left join sync_level level on t.level_code = level.code where 1=1 ");
//        if(!StringUtils.isEmpty(provCode)){
//            sql.append(" and p.code = ? ");
//            param.add(provCode);
//        }
        if(null != sortMap) {
            sql.append(" order by ");
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
            }
        }
        placeOrderPageInfo = super.pageSqlQueryByNativeSql(placeOrderPageInfo, sql.toString(), field, param.toArray());
        return placeOrderPageInfo;
    }
}
