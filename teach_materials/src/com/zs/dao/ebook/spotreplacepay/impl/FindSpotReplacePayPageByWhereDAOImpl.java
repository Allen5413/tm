package com.zs.dao.ebook.spotreplacepay.impl;

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
@Service("findSpotReplacePayPageByWhereDAO")
public class FindSpotReplacePayPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        String field = "srp.id, s.code, s.name, srp.money, srp.pay_way, srp.creator, srp.create_time, srp.verifyer, srp.verify_time, srp.state, srp.remark, srp.imag_url";
        StringBuilder sql = new StringBuilder("FROM spot_replace_pay srp, sync_spot s where srp.spot_code = s.code ");

        String spotCode = paramsMap.get("spotCode");
        String state = paramsMap.get("state");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and s.code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(state)){
            sql.append(" and srp.state = ? ");
            param.add(Integer.parseInt(state));
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
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
