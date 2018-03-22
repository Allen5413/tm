package com.zs.service.basic.issuechannel.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuechannel.IssueChannelDAO;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.DelIssueChannelService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("delIssueChannelService")
public class DelIssueChannelServiceImpl extends EntityServiceImpl<IssueChannel, IssueChannelDAO> implements DelIssueChannelService {
    @Override
    public void delIssueChannel(Long id) throws Exception {
        IssueChannel issueChannel = super.get(id);
        if(null != issueChannel){
            super.remove(issueChannel);
        }
    }
}
