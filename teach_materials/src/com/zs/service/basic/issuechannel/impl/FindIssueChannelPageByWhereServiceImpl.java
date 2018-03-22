package com.zs.service.basic.issuechannel.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.issuechannel.IssueChannelDAO;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.FindIssueChannelPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("findIssueChannelPageByWhereService")
public class FindIssueChannelPageByWhereServiceImpl
        extends EntityServiceImpl<IssueChannel, IssueChannelDAO>
        implements FindIssueChannelPageByWhereService {

    @Resource
    public FindPageByWhereDAO findIssueChannelPageByWhereDAO;

    @Override
    public PageInfo<IssueChannel> findPageByWhere(PageInfo<IssueChannel> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findIssueChannelPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
