package com.zs.dao.basic.issuechannel;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.IssueChannel;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/3.
 */
public interface FindIssueChannelByCodeDAO extends EntityJpaDao<IssueChannel,Long> {
    @Query("from IssueChannel where code = ?1")
    public IssueChannel getIssueChannelByCode(String code);
}
