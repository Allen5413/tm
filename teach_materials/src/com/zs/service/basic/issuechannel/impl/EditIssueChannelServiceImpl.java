package com.zs.service.basic.issuechannel.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.issuechannel.FindIssueChannelByCodeDAO;
import com.zs.dao.basic.issuechannel.FindIssueChannelByNameDAO;
import com.zs.dao.basic.issuechannel.IssueChannelDAO;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.EditIssueChannelService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("editIssueChannelService")
public class EditIssueChannelServiceImpl extends EntityServiceImpl<IssueChannel, IssueChannelDAO> implements EditIssueChannelService {

    @Resource
    private FindIssueChannelByCodeDAO findIssueChannelByCodeDAO;
    @Resource
    private FindIssueChannelByNameDAO findIssueChannelByNameDAO;

    @Override
    public void editIssueChannel(IssueChannel issueChannel, String loginName) throws Exception {
        if(null != issueChannel) {
            //是否存在
            IssueChannel isExistsIssueChannel = super.get(issueChannel.getId());
            if(null == isExistsIssueChannel){
                throw new BusinessException("发行渠道不存在！");
            }
            //查询发行渠道编号是否已经存在
            IssueChannel validIssueChannel = findIssueChannelByCodeDAO.getIssueChannelByCode(issueChannel.getCode());
            if(null != validIssueChannel && validIssueChannel.getCode().equals(issueChannel.getCode()) && issueChannel.getId() != validIssueChannel.getId()){
                throw new BusinessException("发行渠道编号已经存在！");
            }
            //查询发行渠道名称是否已经存在
            validIssueChannel = findIssueChannelByNameDAO.getIssueChannelByName(issueChannel.getName());
            if(null != validIssueChannel && validIssueChannel.getName().equals(issueChannel.getName()) && validIssueChannel.getId() != issueChannel.getId()){
                throw new BusinessException("发行渠道名称已经存在！");
            }
            issueChannel.setOperator(loginName);
            issueChannel.setCreateTime(isExistsIssueChannel.getCreateTime());
            super.update(issueChannel);
        }
    }
}
