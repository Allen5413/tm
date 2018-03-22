package com.zs.dao.placeorder.placeorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/11/11.
 */
public interface EditPlaceOrderForStateDoingDAO extends EntityJpaDao<TeachMaterialPlaceOrder, Long> {
    @Modifying
    @Query("update TeachMaterialPlaceOrder set orderStatus = '2' where orderStatus = '1' and isSpecAll = 1")
    public void editor()throws Exception;
}
