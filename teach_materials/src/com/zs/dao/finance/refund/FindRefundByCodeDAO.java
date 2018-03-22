package com.zs.dao.finance.refund;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Refund;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/1/10 0010.
 */
public interface FindRefundByCodeDAO extends EntityJpaDao<Refund, Long> {
    @Query("from Refund where code = ?1")
    public Refund find(String code);
}
