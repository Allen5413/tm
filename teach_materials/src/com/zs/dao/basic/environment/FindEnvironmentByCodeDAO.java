package com.zs.dao.basic.environment;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Environment;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/13.
 */
public interface FindEnvironmentByCodeDAO extends EntityJpaDao<Environment, Long> {
    @Query("from Environment where code = ?1")
    public Environment getEnvironmentByCode(String code);
}
