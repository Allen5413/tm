package com.zs.service.sync.course.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.sync.course.CourseDAO;
import com.zs.domain.sync.Course;
import com.zs.service.sync.course.FindCoursePageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/19.
 */
@Service("findCoursePageByWhereService")
public class FindCoursePageByWhereServiceImpl extends EntityServiceImpl<Course, CourseDAO>
        implements FindCoursePageByWhereService {

    @Resource
    public FindPageByWhereDAO findCoursePageByWhereDAO;

    @Override
    public PageInfo<Course> findPageByWhere(PageInfo<Course> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findCoursePageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}