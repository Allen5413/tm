package com.zs.dao.placeorder;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
/**
 * 
 * @author yanghaosen
 *
 */
public interface TeachMaterialPlaceOrderDAO extends EntityJpaDao<TeachMaterialPlaceOrder, Long>{
	
	/**
	 * 
	 * @param orderCode
	 * @return
	 */
	@Query("select a from TeachMaterialPlaceOrder a where a.orderCode = ?1")
	public TeachMaterialPlaceOrder findPlaceOrderByCode(String orderCode);
	
	@Query("FROM TeachMaterialPlaceOrder where packageId = ?1")
    public List<TeachMaterialPlaceOrder> findPlaceOrderByPackageId(long packageId);
}
