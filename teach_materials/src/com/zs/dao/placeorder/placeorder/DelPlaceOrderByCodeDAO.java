package com.zs.dao.placeorder.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/3/28.
 */
public interface DelPlaceOrderByCodeDAO extends EntityJpaDao<TeachMaterialPlaceOrder, Long> {
    @Modifying
    @Query("delete from TeachMaterialPlaceOrder where orderCode = ?1 ")
    public void del(String code)throws Exception;
}
