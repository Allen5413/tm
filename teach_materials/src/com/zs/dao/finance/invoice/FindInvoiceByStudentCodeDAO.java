package com.zs.dao.finance.invoice;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/7/14.
 */
public interface FindInvoiceByStudentCodeDAO extends EntityJpaDao<Invoice, Long> {
    @Query("FROM Invoice WHERE studentCode = ?1 ORDER BY createTime DESC")
    public List<Invoice> find(String studentCode);
}
