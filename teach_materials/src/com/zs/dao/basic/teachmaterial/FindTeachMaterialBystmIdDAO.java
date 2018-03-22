package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/5/21.
 */
public interface FindTeachMaterialBystmIdDAO extends EntityJpaDao<TeachMaterial, Long> {
    @Query(nativeQuery = true, value = "select tm.* from teach_material tm, set_teach_material_tm stmtm where stmtm.teach_material_id = tm.id and stmtm.set_teach_material_id = ?1")
    public List<TeachMaterial> getTeachMaterialBystmId(Long stmId);
}
