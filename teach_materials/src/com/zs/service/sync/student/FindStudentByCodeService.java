package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;

/**
 * Created by Allen on 2015/5/10.
 */
public interface FindStudentByCodeService extends EntityService<Student> {
    public Student getStudentByCode(String code);
}
