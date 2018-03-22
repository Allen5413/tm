package com.zs.dao.sale.oncepurchaseorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.OncePurchaseOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/6/22.
 */
public interface FindOncePurchaseOrderByCodeDAO extends EntityJpaDao<OncePurchaseOrder, Long> {
    @Query("FROM OncePurchaseOrder WHERE code = ?1")
    public OncePurchaseOrder find(String code);
}
