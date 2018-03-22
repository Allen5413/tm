package com.zs.service.sync.student.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.student.FindStudentByOpenIdDAO;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.FindStudentByOpenIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/5/24.
 */
@Service("findStudentByOpenIdService")
public class FindStudentByOpenIdServiceImpl extends EntityServiceImpl<Student, StudentDAO> implements FindStudentByOpenIdService {

    @Resource
    private FindStudentByOpenIdDAO findStudentByOpenIdDAO;

    @Override
    public Student find(String openId) {
        return findStudentByOpenIdDAO.find(openId);
    }
}
