package com.zs.dao.sync.spotwx;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SpotWx;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/24.
 */
public interface FindSpotWxByOpenIdDAO extends EntityJpaDao<SpotWx, Long> {
    @Query("from SpotWx where openId = ?1")
    public SpotWx find(String openId);
}
