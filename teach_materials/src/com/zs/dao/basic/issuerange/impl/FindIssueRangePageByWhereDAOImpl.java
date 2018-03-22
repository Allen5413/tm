package com.zs.dao.basic.issuerange.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.IssueRange;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("findIssueRangePageByWhereDAO")
public class FindIssueRangePageByWhereDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo issueRangePageInfo = new PageInfo();
        issueRangePageInfo.setCurrentPage(pageInfo.getCurrentPage());
        issueRangePageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        String field = "s.id, '-' as provName, s.code, s.name as spotName, ic.name as channelName, r.is_issue, r.operator, r.operate_time";
        //StringBuilder sql = new StringBuilder("from sync_spot s left join issue_range r on r.spot_code = s.code left join sync_spot_province sp on s.code = sp.spot_code left join sync_province p on sp.province_code = p.code left join issue_channel ic on r.issue_channel_id = ic.id where 1=1 ");
        StringBuilder sql = new StringBuilder("from sync_spot s left join issue_range r on r.spot_code = s.code left join issue_channel ic on r.issue_channel_id = ic.id where 1=1 ");
        String provCode = paramsMap.get("provCode");
        String spotCode = paramsMap.get("spotCode");
        String channelId = paramsMap.get("issueChannelId");

        List<Object> param = new ArrayList<Object>();
//        if(!StringUtils.isEmpty(provCode)){
//            sql.append(" and p.code = ? ");
//            param.add(provCode);
//        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and s.code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(channelId)){
            sql.append(" and r.issue_channel_id = ? ");
            param.add(Long.parseLong(channelId));
        }
        sql.append(" order by ");
        for (Iterator it = sortMap.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            sql.append(key);
            sql.append(" ");
            sql.append(sortMap.get(key) ? "asc" : "desc");
        }
        issueRangePageInfo = super.pageSqlQueryByNativeSql(issueRangePageInfo, sql.toString(), field, param.toArray());
        return issueRangePageInfo;
    }
}
