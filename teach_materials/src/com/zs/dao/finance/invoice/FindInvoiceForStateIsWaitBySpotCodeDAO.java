package com.zs.dao.finance.invoice;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Invoice;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/9.
 */
public interface FindInvoiceForStateIsWaitBySpotCodeDAO extends EntityJpaDao<Invoice, Long> {

    @Query("FROM Invoice WHERE studentCode is NULL and spotCode = ?1 and state = 0")
    public Invoice find(String spotCode);
}
