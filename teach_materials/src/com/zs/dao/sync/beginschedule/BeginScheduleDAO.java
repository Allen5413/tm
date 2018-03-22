package com.zs.dao.sync.beginschedule;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.BeginSchedule;
import com.zs.domain.sync.Level;
import com.zs.domain.sync.Spec;

/**
 * Created by Allen on 2015/5/8.
 */
public interface BeginScheduleDAO extends EntityJpaDao<BeginSchedule, Long> {
	
	@Query("select a from BeginSchedule a,Semester b where a.academicYear = b.year and a.term = b.quarter and b.isNowSemester = 0 and a.enterYear = ?1 and a.quarter = ?2 and a.specCode = ?3 and a.levelCode = ?4 group by a.enterYear,a.quarter,a.specCode,a.levelCode")
	public List<BeginSchedule> queryBeginScheduleOnGroup(int enYera,int enQuarter,String specCode,String levelCode);
	
	@Query("select DISTINCT a.enterYear from BeginSchedule a order by a.enterYear desc")
	public List<BeginSchedule> queryEnYear();
	
	@Query("select a from Spec a where a.code in (select distinct b.specCode from BeginSchedule b) order by a.code")
	public List<Spec> querySpec();
	
	@Query("select a from Level a where a.code in (select distinct b.levelCode from BeginSchedule b)")
	public List<Level> queryLevel();
}
