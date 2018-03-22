package com.zs.dao.basic.usergroupresource;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.UserGroupResource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/18.
 */
public interface FindUserGroupResourceByResourceIdDAO extends EntityJpaDao<UserGroupResource, Long> {
    @Query("from UserGroupResource where resourceId = ?1")
    public List<UserGroupResource> getUserGroupResourceByResourceId(Long resourceId);
}
