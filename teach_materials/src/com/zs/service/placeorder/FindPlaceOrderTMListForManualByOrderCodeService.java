package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/8/5.
 */
public interface FindPlaceOrderTMListForManualByOrderCodeService extends EntityService {

    public Map<String, Object> findPlaceOrderTMListForManualByOrderCode(String orderCode);

    public Map<String, Object> findPlaceOrderTMListForManualByOrderCode(String orderCode, int isSetState);
}
