package com.zs.dao.placeorder;

import org.springframework.data.jpa.repository.Query;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.placeorder.PlaceOrderPackage;

public interface FindPlaceOrderPackageForMaxSortNotSendDAO extends EntityJpaDao<PlaceOrderPackage, Long>{
	 @Query(nativeQuery = true, value = "select t.* from (select * from place_order_package where spot_code = ?1 and semester_id = ?2 order by sort desc limit 1) t")
	 public PlaceOrderPackage findPlaceOrderPackageForMaxSortNotSend(String spotCode, long semesterId);
}
