package com.zs.service.basic.issuerange.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuerange.IssueRangeDAO;
import com.zs.domain.basic.IssueRange;
import com.zs.service.basic.issuerange.FindIssueRangeService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("findIssueRangeService")
public class FindIssueRangeServiceImpl extends EntityServiceImpl<IssueRange, IssueRangeDAO> implements FindIssueRangeService {
}
