package com.zs.service.basic.issuerange.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuerange.IssueRangeDAO;
import com.zs.domain.basic.IssueRange;
import com.zs.service.basic.issuerange.AddIssueRangeService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("addIssueRangeService")
public class AddIssueRangeServiceImpl extends EntityServiceImpl<IssueRange, IssueRangeDAO> implements AddIssueRangeService {
    @Override
    public void addIssueRange(IssueRange issueRange, String loginName) throws Exception {
        if(null != issueRange) {
            issueRange.setCreator(loginName);
            issueRange.setOperator(loginName);
            super.save(issueRange);
        }
    }
}
