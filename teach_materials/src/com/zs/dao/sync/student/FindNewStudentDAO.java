package com.zs.dao.sync.student;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询新生数据
 * Created by Allen on 2015/5/7.
 */
public interface FindNewStudentDAO extends EntityJpaDao<Student,Long>{
    @Query("from Student where studyEnterYear = ?1 and studyQuarter = ?2")
    public List<Student> getNewStudent(int year, int quarter);
}
