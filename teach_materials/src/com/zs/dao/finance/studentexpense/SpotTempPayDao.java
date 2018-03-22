package com.zs.dao.finance.studentexpense;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.SpotPayTemp;

public interface SpotTempPayDao extends EntityJpaDao<SpotPayTemp,Long>{
	
	@Query("select a from SpotPayTemp a where a.ownId = ?1")
	public List<SpotPayTemp> querySpotPayTempByOwnId(Long payOwnId);
	
	@Query("select a from SpotPayTemp a where a.polId = ?1")
	public List<SpotPayTemp> querySpotPayTempByPolId(Long polId);
}
