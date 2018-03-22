package com.zs.dao.basic.resource;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Resource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/18.
 */
public interface FindResourceByGroupIdDAO extends EntityJpaDao<Resource,Long> {
    @Query(nativeQuery = true, value = "select r.* from resource r, user_group_resource ugr where ugr.resource_id = r.id and ugr.user_group_id = ?1")
    public List<Resource> getResourceByGroupId(Long userGroupId);
}
