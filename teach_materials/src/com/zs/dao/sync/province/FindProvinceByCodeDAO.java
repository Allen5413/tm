package com.zs.dao.sync.province;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Province;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/5/10.
 */
public interface FindProvinceByCodeDAO extends EntityJpaDao<Province, Long> {
    @Query("from Province where code = ?1")
    public Province getProvinceByCode(String code);
}
