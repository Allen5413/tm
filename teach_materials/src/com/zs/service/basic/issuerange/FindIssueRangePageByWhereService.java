package com.zs.service.basic.issuerange;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.IssueRange;

import java.util.Map;

/**
 * Created by Allen on 2015/5/4.
 */
public interface FindIssueRangePageByWhereService extends EntityService {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
