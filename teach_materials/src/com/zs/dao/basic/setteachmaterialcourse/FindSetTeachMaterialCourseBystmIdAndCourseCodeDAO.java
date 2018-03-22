package com.zs.dao.basic.setteachmaterialcourse;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SetTeachMaterialCourse;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/20.
 */
public interface FindSetTeachMaterialCourseBystmIdAndCourseCodeDAO extends EntityJpaDao<SetTeachMaterialCourse, Long> {
    @Query("from SetTeachMaterialCourse where setTeachMaterialId = ?1 and courseCode = ?2")
    public SetTeachMaterialCourse getSetTeachMaterialCourseBystmIdAndCourseCode(long setTeachMaterialId, String courseCode);
}
