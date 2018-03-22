package com.zs.service.basic.setteachmaterialcourse;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SetTeachMaterialCourse;

/**
 * Created by Allen on 2015/5/20.
 */
public interface AddSetTeachMaterialCourseService extends EntityService<SetTeachMaterialCourse> {

    public void addSetTeachMaterialCourse(Long setTeachMaterialId, String courseCodes, String loginName)throws Exception;
}
