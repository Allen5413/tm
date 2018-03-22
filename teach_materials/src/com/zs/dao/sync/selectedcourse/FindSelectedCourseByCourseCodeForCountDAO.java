package com.zs.dao.sync.selectedcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SelectedCourse;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

/**
 * 查询一个时间段类的一个课程的选课数量
 * Created by Allen on 2015/5/8.
 */
public interface FindSelectedCourseByCourseCodeForCountDAO extends EntityJpaDao<SelectedCourse, Long> {
    @Query("select count(*) from SelectedCourse where courseCode = ?1 and unix_timestamp(operatetime) between unix_timestamp(?2) and unix_timestamp(?3)")
    public BigInteger getSelectedCourseByCourseCodeForCount(String courseCode, String beginDate, String endDate);

}
