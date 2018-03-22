package com.zs.service.sync.course;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Course;

import java.util.List;

/**
 * Created by Allen on 2015/5/20.
 */
public interface FindCourseBystmIdService extends EntityService<Course> {
    public List<Course> getCourseBystmId(Long setTeachMaterialId);
}
