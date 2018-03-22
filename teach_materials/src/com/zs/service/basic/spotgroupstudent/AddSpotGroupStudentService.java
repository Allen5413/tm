package com.zs.service.basic.spotgroupstudent;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.SpotGroupStudent;

/**
 * Created by Allen on 2015/5/3.
 */
public interface AddSpotGroupStudentService extends EntityService<SpotGroupStudent> {
    public void addSpotGroupStudent(SpotGroupStudent spotGroupStudent, String loginName)throws Exception;
}
