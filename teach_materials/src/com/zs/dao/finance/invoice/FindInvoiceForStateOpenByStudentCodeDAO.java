package com.zs.dao.finance.invoice;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/5/6.
 */
public interface FindInvoiceForStateOpenByStudentCodeDAO extends EntityJpaDao<Invoice, Long> {

    @Query("FROM Invoice where studentCode = ?1 and state = 1")
    public List<Invoice> find(String code);
}
