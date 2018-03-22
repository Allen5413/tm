package com.zs.dao.placeorder.placeorderlog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/3/28.
 */
public interface DelPlaceOrderLogByOrderIdDAO extends EntityJpaDao<PlaceOrderLog, Long> {
    @Modifying
    @Query("delete from PlaceOrderLog where orderId = ?1 ")
    public void del(long orderId)throws Exception;
}
