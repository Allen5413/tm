package com.zs.service.placeorder;

import java.util.Map;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

public interface FindPlaceOrderPageByWhereService extends EntityService{
	public PageInfo findPage(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
