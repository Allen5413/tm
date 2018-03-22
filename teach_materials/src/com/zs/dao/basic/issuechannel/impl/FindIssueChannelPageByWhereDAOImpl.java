package com.zs.dao.basic.issuechannel.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.IssueChannel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("findIssueChannelPageByWhereDAO")
public class FindIssueChannelPageByWhereDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<IssueChannel> issueChannelPageInfo = new PageInfo<IssueChannel>();
        issueChannelPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        issueChannelPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM IssueChannel where 1=1 ");
        String code = paramsMap.get("code");
        String name = paramsMap.get("name");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(code)){
            sql.append(" and code = ? ");
            param.add(code);
        }
        if(!StringUtils.isEmpty(name)){
            sql.append(" and name like ? ");
            param.add("%"+name+"%");
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
        issueChannelPageInfo = super.pagedQueryByJpql(issueChannelPageInfo, sql.toString(), param.toArray());
        return issueChannelPageInfo;
    }
}

