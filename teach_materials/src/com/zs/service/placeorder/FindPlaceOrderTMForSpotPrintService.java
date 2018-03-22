package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Allen on 2015/8/1.
 */
public interface FindPlaceOrderTMForSpotPrintService extends EntityService {
    public JSONObject findPlaceOrderTMForSpotPrint(HttpServletRequest request, Map<String, String> paramsMap, Map<String, Boolean> sortMap)throws Exception;
}
