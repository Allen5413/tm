package com.zs.dao.basic.spotteachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SpotTeachMaterial;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/15.
 */
public interface FindSpotTeachMaterialByTMIdDAO extends EntityJpaDao<SpotTeachMaterial, Long> {
    @Query("from SpotTeachMaterial where teachMaterialId = ?1")
    public SpotTeachMaterial getSpotTeachMaterialByTMId(Long tmId);
}
