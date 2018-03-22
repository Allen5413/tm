package com.zs.service.basic.courseteachmaterial;

import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2015/12/14 0014.
 */
public interface AddCourseTeachMaterialService extends EntityService {
    public void add(long semesterId, String userName)throws Exception;
}
