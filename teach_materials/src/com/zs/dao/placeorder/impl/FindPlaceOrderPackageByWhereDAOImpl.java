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

@Service("findPlaceOrderPackageByWhereDAOImpl")
public class FindPlaceOrderPackageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO{

	@Override
	public PageInfo findPageByWhere(PageInfo pageInfo,
			Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
		PageInfo studentOrderPageInfo = new PageInfo();
        studentOrderPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        studentOrderPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());
        
        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String code = paramsMap.get("code");
        String logisticCode = paramsMap.get("logisticCode");
        String isSign = paramsMap.get("isSign");
        String beginSendDate = paramsMap.get("beginSendDate");
        String endSendDate = paramsMap.get("endSendDate");
        String isSend = paramsMap.get("isSend");
        
        String field = "t.*";
        StringBuilder sql = new StringBuilder("FROM (select sp.name, kp.data, pop.*, count(tmpo.order_code) as orderCount ");
        sql.append("FROM place_order_package pop ");
        sql.append("LEFT JOIN kuaidi_push kp on pop.logistic_code = kp.nu ");
        sql.append("INNER JOIN sync_spot sp on pop.spot_code = sp.code ");
        sql.append("INNER JOIN teach_material_place_order tmpo on tmpo.package_id = pop.id ");
        sql.append("WHERE 1=1 ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("AND pop.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and pop.spot_code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(code)){
            sql.append("AND pop.code = ? ");
            param.add(code);
        }
        if(!StringUtils.isEmpty(logisticCode)){
            sql.append("AND pop.logistic_code = ? ");
            param.add(logisticCode);
        }
        if(!StringUtils.isEmpty(isSend)){
            //查询还没有邮寄的
            if("0".equals(isSend)){
                sql.append("and pop.logistic_code IS NULL ");
            }
            //查询已经邮寄的
            if("1".equals(isSend)){
                sql.append("and pop.logistic_code IS NOT NULL ");
            }
        }
        if(!StringUtils.isEmpty(isSign)){
            sql.append("AND pop.is_sign = ? ");
            param.add(Integer.parseInt(isSign));
        }
        if(!StringUtils.isEmpty(beginSendDate)){
            sql.append("AND pop.send_time >= ? ");
            param.add(beginSendDate+" 00:00:00");
        }
        if(!StringUtils.isEmpty(endSendDate)){
            sql.append("AND pop.send_time <= ? ");
            param.add(endSendDate+" 23:59:59");
        }
        sql.append("group by name, data, id, semester_id, code, spot_code, sort, logistic_code, is_sign, send_time, creator, create_time, operator, operate_time, version having(count(tmpo.order_code) > 0) ");
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
        sql.append(") t");
        studentOrderPageInfo = super.pageSqlQueryByNativeSql(studentOrderPageInfo, sql.toString(), field, param.toArray());
        return studentOrderPageInfo;
	}

}
