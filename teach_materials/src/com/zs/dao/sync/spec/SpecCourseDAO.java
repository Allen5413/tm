package com.zs.dao.sync.spec;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SpecCourse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/10/20.
 */
public interface SpecCourseDAO extends EntityJpaDao<SpecCourse, Long> {
    @Query("FROM SpecCourse GROUP BY specCode, levelCode")
    public List<SpecCourse> findSpecLevel();
}