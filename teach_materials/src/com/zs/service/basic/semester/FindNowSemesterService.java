package com.zs.service.basic.semester;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Semester;

/**
 * Created by Allen on 2015/5/23.
 */
public interface FindNowSemesterService extends EntityService<Semester> {
    public Semester getNowSemester();
}
