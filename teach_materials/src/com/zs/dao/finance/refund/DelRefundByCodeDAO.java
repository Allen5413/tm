package com.zs.dao.finance.refund;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Refund;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/12/12.
 */
public interface DelRefundByCodeDAO extends EntityJpaDao<Refund, Long> {
    @Modifying
    @Query("delete from Refund where code = ?1")
    public void del(String code);
}
