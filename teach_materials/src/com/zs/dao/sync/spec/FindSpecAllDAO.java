package com.zs.dao.sync.spec;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Spec;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
public interface FindSpecAllDAO extends EntityJpaDao<Spec, Long> {
    @Query("from Spec order by code")
    public List<Spec> getSpecAll();
}
