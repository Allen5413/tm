package com.zs.dao.basic.teachmaterial;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.basic.TeachMaterial;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 通过教材名称查询教材信息
 * Created by Allen on 2015/4/29.
 */
public interface FindTeachMaterialByNameDAO extends EntityJpaDao<TeachMaterial,Long> {
    @Query("from TeachMaterial where name = ?1")
    public List<TeachMaterial> getTeachMaterialByName(String name);
}
