package com.zs.service.sync.student.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.FindStudentByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/10.
 */
@Service("findStudentByCodeService")
public class FindStudentByCodeServiceImpl
    extends EntityServiceImpl<Student, FindStudentByCodeDAO>
    implements FindStudentByCodeService{

    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;

    @Override
    public Student getStudentByCode(String code) {
        return findStudentByCodeDAO.getStudentByCode(code);
    }
}
