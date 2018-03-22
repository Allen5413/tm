package com.zs.dao.basic.teachmaterialtype;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterialType;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/3.
 */
public interface FindTeachMaterialTypeByCodeDAO extends EntityJpaDao<TeachMaterialType,Long> {
    @Query("from TeachMaterialType where code = ?1")
    public TeachMaterialType getTeachMaterialTypeByCode(String code);
}
