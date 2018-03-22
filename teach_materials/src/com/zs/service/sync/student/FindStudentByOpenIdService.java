package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;

/**
 * Created by Allen on 2016/5/24.
 */
public interface FindStudentByOpenIdService extends EntityService<Student> {
    public Student find(String openId);
}
