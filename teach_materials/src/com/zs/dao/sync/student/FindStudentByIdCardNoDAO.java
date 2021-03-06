package com.zs.dao.sync.student;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/5/24.
 */
public interface FindStudentByIdCardNoDAO extends EntityJpaDao<Student, Long> {
    @Query("from Student where idcardNo = ?1 and state = 0")
    public Student find(String idCardNo);

    @Query("from Student where idcardNo = ?1 and state = 0 order by operateTime desc")
    public List<Student> findList(String idCardNo);
}
