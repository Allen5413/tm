package com.zs.dao.basic.setteachmaterialtm;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.SetTeachMaterialTM;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/20.
 */
public interface DelSetTeachMaterialTMBystmIdDAO extends EntityJpaDao<SetTeachMaterialTM, Long> {
    @Modifying
    @Query("delete from SetTeachMaterialTM where setTeachMaterialId = ?1")
    public void delSetTeachMaterialTMBystmId(Long stmId);
}
