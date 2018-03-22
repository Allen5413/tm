package com.zs.service.sync.course.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.course.FindCourseBystmIdDAO;
import com.zs.domain.sync.Course;
import com.zs.service.sync.course.FindCourseBystmIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/5/20.
 */
@Service("findCourseBystmIdService")
public class FindCourseBystmIdServiceImpl extends EntityServiceImpl<Course, FindCourseBystmIdDAO> implements FindCourseBystmIdService {

    @Resource
    private FindCourseBystmIdDAO findCourseBystmIdDAO;

    @Override
    public List<Course> getCourseBystmId(Long setTeachMaterialId) {
        return findCourseBystmIdDAO.getCourseBystmId(setTeachMaterialId);
    }
}
