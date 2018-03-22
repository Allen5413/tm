package com.zs.service.sync.course.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.course.FindCourseBytmIdDAO;
import com.zs.domain.sync.Course;
import com.zs.service.sync.course.FindCourseBytmIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/19.
 */
@Service("findCourseBytmIdService")
public class FindCourseBytmIdServiceImpl extends EntityServiceImpl<Course, FindCourseBytmIdDAO> implements FindCourseBytmIdService {

    @Resource
    private FindCourseBytmIdDAO findCourseBytmIdDAO;

    @Override
    public List<Course> getCourseBytmId(Long teachMaterialId) {
        return findCourseBytmIdDAO.getCourseBytmId(teachMaterialId);
    }
}
