package com.zs.dao.sync.spot;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sync.Spot;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/3/24.
 */
public interface FindSpotByBankCodeDAO extends EntityJpaDao<Spot, Long> {
    @Query("from Spot where bankCode = ?1 ")
    public Spot find(String bankCode);
}