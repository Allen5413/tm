package com.zs.service.basic.issuerange.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuerange.IssueRangeDAO;
import com.zs.domain.basic.IssueRange;
import com.zs.service.basic.issuerange.DelIssueRangeService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("delIssueRangeService")
public class DelIssueRangeServiceImpl extends EntityServiceImpl<IssueRange, IssueRangeDAO> implements DelIssueRangeService {
    @Override
    public void delIssueRange(Long id) throws Exception {
        IssueRange issueRange = super.get(id);
        if(null != issueRange){
            super.remove(issueRange);
        }
    }
}
