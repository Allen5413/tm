package com.zs.dao.sync.province;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Province;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/6/4.
 */
public interface FindProvinceAllDAO extends EntityJpaDao<Province, Long> {
    @Query("from Province order by code")
    public List<Province> getProvinceAll();
}
