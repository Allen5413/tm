package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;
import net.sf.json.JSONObject;

/**
 * Created by Allen on 2015/10/20.
 */
public interface FindStudentInfoService extends EntityService<Student> {
    public JSONObject findStudentInfo(String code, String spotCode)throws Exception;
}
