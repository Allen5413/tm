package com.zs.dao.basic.setteachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SetTeachMaterial;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/4/30.
 */
public interface FindSetTeachMaterialByNameDAO extends EntityJpaDao<SetTeachMaterial,Long> {
    @Query("from SetTeachMaterial where name = ?1")
    public SetTeachMaterial getTeachMaterialByName(String name);
}
