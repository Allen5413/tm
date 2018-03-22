package com.zs.dao.sync.course;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Course;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
public interface FindCourseAllDAO extends EntityJpaDao<Course, Long> {
    @Query("from Course order by code")
    public List<Course> getCourseAll();
}
