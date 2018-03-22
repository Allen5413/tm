package com.zs.dao.sync.selectedcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SelectedCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询一个学期下面的选课
 * Created by Allen on 2015/5/12.
 */
public interface FindSelectedCourseBySemesterIdDAO extends EntityJpaDao<SelectedCourse, Long> {
    @Query("select sc from SelectedCourse sc, Student s where sc.studentCode = s.code and s.spotCode = '001' and sc.semesterId = ?1")
    public List<SelectedCourse> getSelectedCourseBySemesterId(long semesterId);
}
