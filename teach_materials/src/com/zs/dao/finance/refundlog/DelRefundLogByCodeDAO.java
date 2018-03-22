package com.zs.dao.finance.refundlog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.RefundLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/12/12.
 */
public interface DelRefundLogByCodeDAO extends EntityJpaDao<RefundLog, Long> {
    @Modifying
    @Query("delete from RefundLog where code = ?1")
    public void del(String code);
}
