package com.zs.service.sync.spec;

import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.SpecCourse;

/**
 * Created by Allen on 2016/10/20.
 */
public interface FindSpecCourseTMBySpecAndLevelService extends EntityService<SpecCourse> {
    public JSONObject find(String specCode, String levelCode);
}
