package com.zs.dao.sync.spot;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Spot;

/**
 * Created by Allen on 2015/5/22.
 */
public interface SpotDAO extends EntityJpaDao<Spot, Long> {
	
	@Query("select a from Spot a where a.code = ?1")
	public Spot querySpotByCode(String spotCode);
}
