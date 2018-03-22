package com.zs.dao.sync.student;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Student;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/24.
 */
public interface FindStudentByOpenIdDAO extends EntityJpaDao<Student, Long> {
    @Query("from Student where openId = ?1")
    public Student find(String openId);
}
