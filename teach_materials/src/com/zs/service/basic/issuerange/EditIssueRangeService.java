package com.zs.service.basic.issuerange;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.IssueRange;

/**
 * Created by Allen on 2015/5/4.
 */
public interface EditIssueRangeService extends EntityService<IssueRange> {
    public void editIssueRange(IssueRange issueRange, String loginName)throws Exception;
}
