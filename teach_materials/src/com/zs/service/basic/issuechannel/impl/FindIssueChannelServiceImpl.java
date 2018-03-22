package com.zs.service.basic.issuechannel.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuechannel.IssueChannelDAO;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("findIssueChannelService")
public class FindIssueChannelServiceImpl extends EntityServiceImpl<IssueChannel, IssueChannelDAO> implements FindIssueChannelService {
}
