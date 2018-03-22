package com.zs.dao.placeorder;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderPackage;

public interface FindPlaceOrderPackageForMaxCodeDAO extends EntityJpaDao<PlaceOrderPackage, Long>{
	 @Query(nativeQuery = true, value = "select t.* from (select * from place_order_package where code like ? order by code desc limit 1) t")
	 public PlaceOrderPackage findPlaceOrderPackageForMaxCode(String codePrefix);
}
