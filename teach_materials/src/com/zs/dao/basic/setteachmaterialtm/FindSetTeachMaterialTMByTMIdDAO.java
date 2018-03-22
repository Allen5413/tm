package com.zs.dao.basic.setteachmaterialtm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SetTeachMaterialTM;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/11/22.
 */
public interface FindSetTeachMaterialTMByTMIdDAO extends EntityJpaDao<SetTeachMaterialTM, Long> {
    @Query("from SetTeachMaterialTM where teachMaterialId = ?1")
    public List<SetTeachMaterialTM> find(long tmId);
}
