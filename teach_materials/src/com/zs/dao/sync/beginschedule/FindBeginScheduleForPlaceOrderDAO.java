package com.zs.dao.sync.beginschedule;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.BeginSchedule;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/8/4.
 */
public interface FindBeginScheduleForPlaceOrderDAO extends EntityJpaDao<BeginSchedule,Long> {
    @Query(nativeQuery = true, value = "select DISTINCT enter_year, `quarter`, academic_year, term, spec_code, level_code, course_code " +
            "from sync_begin_schedule where academic_year = ?1 and term = ?2 and enter_year = ?3 and quarter = ?4 and spec_code = ?5 and level_code = ?6")
    public List<Object[]> findBeginScheduleForPlaceOrder(int year, int quarter, int enYear, int enQuarter, String specCode, String levelCode);
}
