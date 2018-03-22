package com.zs.dao.basic.semester;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.Semester;

/**
 * Created by Allen on 2015/5/7.
 */
public interface SemesterDAO extends EntityJpaDao<Semester,Long> {
	
	@Query("select a from Semester a where a.isNowSemester = 0")
	public Semester queryLocalSemester();
}
