package com.zs.dao.sync.level;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Level;

/**
 * Created by Allen on 2015/5/26.
 */
public interface LevelDAO extends EntityJpaDao<Level, Long> {
	
	@Query("select a from Level a where a.code = ?1")
	public Level queryLevelByCode(String code);
}
