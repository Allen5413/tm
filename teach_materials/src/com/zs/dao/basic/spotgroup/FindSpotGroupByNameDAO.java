package com.zs.dao.basic.spotgroup;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SpotGroup;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/1.
 */
public interface FindSpotGroupByNameDAO extends EntityJpaDao<SpotGroup,Long> {
    @Query("from SpotGroup where name = ?1 and spotCode = ?2")
    public SpotGroup getSpotGroupByName(String name, String spotCode);
}
