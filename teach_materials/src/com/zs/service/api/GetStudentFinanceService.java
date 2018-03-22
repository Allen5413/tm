package com.zs.service.api;

import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;

/**
 * Created by Allen on 2017/5/27.
 */
public interface GetStudentFinanceService extends EntityService<Student> {
    public JSONObject get(String code) throws Exception;
}
