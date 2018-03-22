package com.zs.dao.finance.refund.impl;

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
 * Created by Allen on 2016/1/4.
 */
@Service("findRefundPageByWhereDAO")
public class FindRefundPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        String field = "t.*";
        StringBuilder sql = new StringBuilder("from (select r.id, r.spot_code, s.name, r.code, r.bank_name, r.bank_code, r.company, r.state, r.creator, r.create_time, r.operator, r.operate_time, count(*), ROUND(sum(rs.money), 2) from refund r, refund_student rs, sync_spot s where r.code = rs.code and r.spot_code = s.code ");

        String code = paramsMap.get("code");
        String spotCode = paramsMap.get("spotCode");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");
        String state = paramsMap.get("state");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and r.spot_code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(code)){
            sql.append(" and r.code = ? ");
            param.add(code);
        }
        if(!StringUtils.isEmpty(beginDate)){
            sql.append(" and r.create_time >= ? ");
            param.add(beginDate+" 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)){
            sql.append(" and r.create_time <= ? ");
            param.add(endDate+" 23:59:59");
        }
        if(!StringUtils.isEmpty(state)){
            sql.append(" and r.state = ? ");
            param.add(Integer.parseInt(state));
        }
        sql.append("group by r.id, r.spot_code, s.name, r.code, r.bank_name, r.bank_code, r.company, r.state, r.creator, r.create_time, r.operator, r.operate_time) t ");
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
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
