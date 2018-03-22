package com.zs.dao.finance.invoice;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Invoice;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/6.
 */
public interface FindInvoiceByCodeDAO extends EntityJpaDao<Invoice, Long> {

    @Query("FROM Invoice where code = ?1")
    public Invoice find(String code);
}
