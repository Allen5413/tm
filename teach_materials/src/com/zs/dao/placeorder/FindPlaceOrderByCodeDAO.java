package com.zs.dao.placeorder;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.domain.sale.StudentBookOrder;

public interface FindPlaceOrderByCodeDAO extends EntityJpaDao<TeachMaterialPlaceOrder, Long>{
	
	@Query("FROM TeachMaterialPlaceOrder where orderCode = ?1")
    public TeachMaterialPlaceOrder findPlaceOrderByCode(String orderCode);
}
