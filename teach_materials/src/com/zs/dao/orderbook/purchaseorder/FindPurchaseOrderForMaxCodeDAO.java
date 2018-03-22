package com.zs.dao.orderbook.purchaseorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.orderbook.PurchaseOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * 查询一个学期，订单号最大的一个，下次生成订单号时，累计后面流水号
 * Created by Allen on 2015/5/12.
 */
public interface FindPurchaseOrderForMaxCodeDAO extends EntityJpaDao<PurchaseOrder, Long> {
    @Query(nativeQuery = true, value = "select t.* from (select * from purchase_order where semester_Id = ?1 order by code desc limit 0,1) t")
    public PurchaseOrder getPurchaseOrderForMaxCode(Long semesterId);
}
