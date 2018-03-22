package com.zs.dao.finance.studentexpense;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.SpotPayPolTemp;

public interface SpotPayPolTempDao extends EntityJpaDao<SpotPayPolTemp,Long>{
	
	@Query("select a from SpotPayPolTemp a")
	public List<SpotPayPolTemp> querySpotPayPolTemp();
	
	@Query("select a from SpotPayPolTemp a where a.ownId =?1 order by a.createTime desc")
	public List<SpotPayPolTemp> querySpotPayPolTempByOwnId(Long ownId);

	@Query("select a from SpotPayPolTemp a where a.ownId <= 0 and a.spotCode = ?1 and a.isSure = 0 order by a.createTime desc")
	public List<SpotPayPolTemp> querySpotPayPolTempBySpotCodeForIsSureWait(String spotCode);
}
