package com.zs.dao.sync.student;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 通过学号查询学生信息
 * Created by Allen on 2015/5/9.
 */
public interface FindStudentByCodeDAO extends EntityJpaDao<Student,Long> {
    @Query("from Student where code = ?1")
    public Student getStudentByCode(String code);
}
