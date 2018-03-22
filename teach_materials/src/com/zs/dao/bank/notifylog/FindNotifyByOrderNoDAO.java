package com.zs.dao.bank.notifylog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.bank.BankNotifyLog;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/3.
 */
public interface FindNotifyByOrderNoDAO extends EntityJpaDao<BankNotifyLog, Long> {
    @Query("from BankNotifyLog where orderNo = ?1")
    public BankNotifyLog find(String orderNo);
}
