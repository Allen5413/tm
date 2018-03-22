package com.zs.dao.sync.eduwestuser;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.EduwestUser;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/10.
 */
public interface FindEduwestUserByPinDAO extends EntityJpaDao<EduwestUser, Long> {
    @Query("from EduwestUser where pin = ?1")
    public EduwestUser getEduwestUserByPin(String pin);
}
