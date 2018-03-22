package com.zs.dao.sync.eduwestuser;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.EduwestUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/8/18.
 */
public interface FindEduwestUserBySpotCodeDAO extends EntityJpaDao<EduwestUser, Long> {
    @Query("from EduwestUser where spotCode = ?1")
    public List<EduwestUser> getEduwestUserBySpotCode(String spotCode);
}
