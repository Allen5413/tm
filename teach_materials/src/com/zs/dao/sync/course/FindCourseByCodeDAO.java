package com.zs.dao.sync.course;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Course;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/10/23.
 */
public interface FindCourseByCodeDAO extends EntityJpaDao<Course, Long> {
    @Query("FROM Course WHERE code = ?1")
    public Course find(String code);
}
