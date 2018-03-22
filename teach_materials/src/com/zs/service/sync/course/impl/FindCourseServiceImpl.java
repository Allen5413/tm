package com.zs.service.sync.course.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.course.CourseDAO;
import com.zs.dao.sync.course.FindCourseAllDAO;
import com.zs.domain.sync.Course;
import com.zs.service.sync.course.FindCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/19.
 */
@Service("findCourseService")
public class FindCourseServiceImpl extends EntityServiceImpl<Course, CourseDAO> implements FindCourseService {

    @Resource
    private FindCourseAllDAO findCourseAllDAO;

    @Override
    public List<Course> getAll(){
        return findCourseAllDAO.getCourseAll();
    }
}
