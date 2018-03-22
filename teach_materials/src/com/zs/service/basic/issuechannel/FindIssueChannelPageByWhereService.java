package com.zs.service.basic.issuechannel;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.IssueChannel;

import java.util.Map;

/**
 * Created by Allen on 2015/5/3.
 */
public interface FindIssueChannelPageByWhereService extends EntityService<IssueChannel> {
    public PageInfo<IssueChannel> findPageByWhere(PageInfo<IssueChannel> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
