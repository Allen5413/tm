package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

import java.util.Map;

/**
 * Created by Allen on 2015/8/1.
 */
public interface FindPlaceOrderForSpotPrintService extends EntityService {
    public JSONArray findPlaceOrderForSpotPrint(Map<String, String> paramsMap, Map<String, Boolean> sortMap);
}
