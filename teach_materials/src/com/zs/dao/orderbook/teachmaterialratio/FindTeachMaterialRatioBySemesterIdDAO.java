package com.zs.dao.orderbook.teachmaterialratio;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.orderbook.TeachMaterialRatio;
import org.springframework.data.jpa.repository.Query;

/**
* Created by Allen on 2015/5/5.
*/
public interface FindTeachMaterialRatioBySemesterIdDAO extends EntityJpaDao<TeachMaterialRatio,Long> {
    @Query("from TeachMaterialRatio where semesterId = ?1")
    public TeachMaterialRatio getTeachMaterialRatioBySemesterId(Long semesterId);
}
