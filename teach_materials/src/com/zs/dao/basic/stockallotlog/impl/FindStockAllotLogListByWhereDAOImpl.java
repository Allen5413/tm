package com.zs.dao.basic.stockallotlog.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/28.
 */
@Service("findStockAllotLogListByWhereDAO")
public class FindStockAllotLogListByWhereDAOImpl extends BaseQueryDao implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select ic.name as afterChannelName, ic2.name as beforeChannelName, sal.stock, sal.operator, sal.operate_time from ");
        sbSql.append("stock_allot_log sal, issue_channel ic, issue_channel ic2 where ");
        sbSql.append("sal.old_issue_channel_id = ic.id and sal.new_issue_channel_id = ic2.id and sal.teach_material_id = ? ");

        String tmId = paramsMap.get("tmId");

        List<Object> params = new ArrayList<Object>();
        params.add(Long.parseLong(tmId));

        if(null != sortMap) {
            sbSql.append("order by ");
            int i = 0;
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                if(0 < i){
                    sbSql.append(",");
                }
                String key = it.next().toString();
                sbSql.append(key);
                sbSql.append(" ");
                sbSql.append(sortMap.get(key) ? "asc" : "desc");
                i++;
            }
        }
        List<Object[]> list = super.sqlQueryByNativeSql(sbSql.toString(), params.toArray());
        return list;
    }
}
