package com.zs.service.basic.issuerange.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuerange.IssueRangeDAO;
import com.zs.domain.basic.IssueRange;
import com.zs.service.basic.issuerange.EditIssueRangeService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/4.
 */
@Service("editIssueRangeService")
public class EditIssueRangeServiceImpl extends EntityServiceImpl<IssueRange, IssueRangeDAO> implements EditIssueRangeService {
    @Override
    public void editIssueRange(IssueRange issueRange, String loginName) throws Exception {
        if(null != issueRange) {
            //是否存在
            IssueRange isExistsIssueRange = super.get(issueRange.getId());
            if(null == isExistsIssueRange){
                throw new BusinessException("发行范围不存在！");
            }
            issueRange.setSpotCode(isExistsIssueRange.getSpotCode());
            issueRange.setOperator(loginName);
            issueRange.setCreator(isExistsIssueRange.getCreator());
            issueRange.setCreateTime(isExistsIssueRange.getCreateTime());
            super.update(issueRange);
        }
    }
}
