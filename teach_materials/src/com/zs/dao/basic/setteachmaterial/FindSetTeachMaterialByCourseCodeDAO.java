package com.zs.dao.basic.setteachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SetTeachMaterial;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/11.
 */
public interface FindSetTeachMaterialByCourseCodeDAO extends EntityJpaDao<SetTeachMaterial,Long> {
    @Query("from SetTeachMaterial where buyCourseCode = ?1")
    public SetTeachMaterial getSetTeachMaterialByCourseCode(String courseCode);
}
