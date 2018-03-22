package com.zs.dao.sale.oncepurchaseorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.OncePurchaseOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询一个学期，采购单号最大的一个，下次生成采购单号时，累计后面流水号
 * Created by Allen on 2016/6/16.
 */
public interface FindOncePurchaseOrderForMaxCodeDAO extends EntityJpaDao<OncePurchaseOrder, Long> {
    @Query(nativeQuery = true, value = "select t.* from (select * from once_purchase_order where semester_id = ?1 order by RIGHT(code,6) desc limit 1) t")
    public OncePurchaseOrder find(Long semesterId);
}
