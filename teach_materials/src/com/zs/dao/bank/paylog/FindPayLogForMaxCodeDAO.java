package com.zs.dao.bank.paylog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.bank.BankPayLog;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/10/27.
 */
public interface FindPayLogForMaxCodeDAO extends EntityJpaDao<BankPayLog, Long> {
    @Query(nativeQuery = true, value = "select * from bank_pay_log order by code desc limit 1")
    public BankPayLog find();
}
