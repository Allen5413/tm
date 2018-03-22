package com.zs.service.placeorder;

import com.zs.service.placeorder.bean.PlaceOrderDetailShow;
import net.sf.json.JSONArray;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface FindPlaceOrderListByOrderCodeService extends EntityService{
		
	public Map<String, Object> getPlaceOrderListByOrderCode(String orderCode, int isGLTM);
}
