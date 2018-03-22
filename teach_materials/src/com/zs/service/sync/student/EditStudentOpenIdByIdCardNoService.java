package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;
import net.sf.json.JSONObject;

import java.sql.Timestamp;

/**
 * Created by Allen on 2016/5/24.
 */
public interface EditStudentOpenIdByIdCardNoService extends EntityService<Student> {
    public JSONObject edit(String idCardNo, String openId, Timestamp wxBoundTime)throws Exception;

    public JSONObject cancelBound(String openId)throws Exception;
}
