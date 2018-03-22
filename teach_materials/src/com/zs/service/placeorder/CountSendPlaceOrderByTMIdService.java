package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

import java.util.Map;

/**
 * Created by Allen on 2015/9/16.
 */
public interface CountSendPlaceOrderByTMIdService extends EntityService {
    public JSONArray countSendPlaceOrderByTMId(Map<String, String> paramsMap, Map<String, Boolean> sortMap);
}
