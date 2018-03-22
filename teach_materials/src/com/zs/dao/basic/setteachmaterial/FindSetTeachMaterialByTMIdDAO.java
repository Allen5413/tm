package com.zs.dao.basic.setteachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SetTeachMaterial;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/7/14.
 */
public interface FindSetTeachMaterialByTMIdDAO extends EntityJpaDao<SetTeachMaterial,Long> {
    @Query(nativeQuery = true, value = "select t.* from set_teach_material t, set_teach_material_tm tm where t.id = tm.set_teach_material_id and tm.teach_material_id = ?1 limit 1")
    public SetTeachMaterial findSetTeachMaterialByTMId(long  tmId);
}
