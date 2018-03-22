package com.zs.dao.basic.spotgroupstudent;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SpotGroupStudent;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/3.
 */
public interface FindSpotGroupStudentByStudentCodeDAO
        extends EntityJpaDao<SpotGroupStudent,Long> {
    @Query("from SpotGroupStudent where studentCode = ?1")
    public SpotGroupStudent getSpotGroupStudentByStudentCode(String studentCode);
}
