package com.zs.dao.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlaceOrderLogDAO extends EntityJpaDao<PlaceOrderLog, Long>{
    /**
     * 在订单状态改为已处理的时候，插入状态变更记录
     * @param loginName
     * @throws Exception
     */
    @Modifying
    @Query(nativeQuery = true, value = "INSERT into place_order_log(order_id, state, operator, operate_time) select id, 2, ?1, now() from teach_material_place_order where order_status = 1 and semester_id = ?2 and is_spec_all = 1")
    public void addForStateToDoing(String loginName, long semesterId) throws Exception;
}
