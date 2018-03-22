package com.zs.dao.sync.province;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Province;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/6/1.
 */
public interface FindProvinceBySpotCodeDAO extends EntityJpaDao<Province, Long> {
    @Query(nativeQuery = true, value = "select p.* from sync_province p, sync_spot_province sp where p.code = sp.province_code and sp.spot_code = ?1")
    public Province getProvinceBySpotCode(String spotCode);
}
