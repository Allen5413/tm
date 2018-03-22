package com.zs.service.basic.teachmaterialcourse;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.TeachMaterialCourse;

/**
 * Created by Allen on 2015/5/19.
 */
public interface AddTeachMaterialCourseService extends EntityService<TeachMaterialCourse> {

    public void addTeachMaterialCourse(Long teachMaterialId, String courseCodes, String loginName)throws Exception;
}
