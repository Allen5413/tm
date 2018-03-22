package com.zs.dao.sync.beginschedule;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.BeginSchedule;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 获取某一个学期的开课计划
 * Created by Allen on 2015/5/8.
 */
public interface FindBeginScheduleByNowSemesterDAO extends EntityJpaDao<BeginSchedule,Long> {
    @Query("from BeginSchedule where academicYear = ?1 and term = ?2")
    public List<BeginSchedule> getBeginScheduleByNowSemester(int year, int quarter);
}
