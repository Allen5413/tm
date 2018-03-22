package com.zs.dao.sync.spec;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Spec;

/**
 * Created by Allen on 2015/5/26.
 */
public interface SpecDAO extends EntityJpaDao<Spec, Long> {
	
	@Query("select a from Spec a where a.code = ?1")
	public Spec querySpecByCode(String specCode);
}
