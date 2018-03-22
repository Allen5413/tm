package com.zs.service.sync.course;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Course;

import java.util.Map;

/**
 * Created by Allen on 2015/5/19.
 */
public interface FindCoursePageByWhereService extends EntityService<Course> {
    public PageInfo<Course> findPageByWhere(PageInfo<Course> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
