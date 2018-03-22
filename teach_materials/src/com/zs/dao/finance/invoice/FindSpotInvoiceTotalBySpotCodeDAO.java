package com.zs.dao.finance.invoice;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.finance.Invoice;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/5/9.
 */
public interface FindSpotInvoiceTotalBySpotCodeDAO extends EntityJpaDao<Invoice, Long> {
    @Query(nativeQuery = true, value = "select ifnull(sum(money),0) from invoice where spot_code = ?1")
    public double find(String spotCode);

    @Query(nativeQuery = true, value = "select ifnull(sum(money),0) from invoice where state = 1 and spot_code = ?1")
    public double findForStateOpen(String spotCode);
}
