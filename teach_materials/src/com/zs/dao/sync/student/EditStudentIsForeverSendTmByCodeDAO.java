package com.zs.dao.sync.student;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2018/9/6.
 */
public interface EditStudentIsForeverSendTmByCodeDAO extends EntityJpaDao<Student,Long> {
    @Modifying
    @Query(nativeQuery = true, value = "update sync_student set is_forever_sned_tm = ?1 where code = ?2")
    public void edit(int isForeverSendTm, String code);
}
