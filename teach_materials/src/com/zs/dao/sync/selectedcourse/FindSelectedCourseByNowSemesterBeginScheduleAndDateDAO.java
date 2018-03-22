package com.zs.dao.sync.selectedcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SelectedCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询当前学期开课计划一个时间范围内的选课信息
 * Created by Allen on 2015/5/8.
 */
public interface FindSelectedCourseByNowSemesterBeginScheduleAndDateDAO extends EntityJpaDao<SelectedCourse, Long> {
    @Query(nativeQuery=true, value="select sc.* from sync_begin_schedule bs, sync_selected_course sc " +
            "where bs.course_code = sc.course_code and bs.academic_year = ?1 and bs.term = ?2 " +
            "and unix_timestamp(sc.operate_time) between unix_timestamp(?3) and unix_timestamp(?4)")
    public List<SelectedCourse> getSelectedCourseByNowSemesterBeginScheduleAndDate(int year, int quarter, String beginDate, String endDate);
}
