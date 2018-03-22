package com.zs.dao.sync.spotwx;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.SpotWx;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/9/8.
 */
public interface FindSpotWxByIdCardDAO  extends EntityJpaDao<SpotWx, Long> {
    @Query("from SpotWx where idcard = ?1")
    public SpotWx find(String idcard);
}
