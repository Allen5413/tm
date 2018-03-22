package com.zs.service.basic.issuechannel;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.IssueChannel;

/**
 * Created by Allen on 2015/5/3.
 */
public interface EditIssueChannelService extends EntityService<IssueChannel> {

    public void editIssueChannel(IssueChannel issueChannel, String loginName)throws Exception;
}
