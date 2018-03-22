package com.zs.service.sale.onceordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Allen on 2015/7/19.
 */
public interface FindOnceOrderTMForSpotPrintService extends EntityService {
    public JSONObject find(HttpServletRequest request, Map<String, String> paramsMap, Map<String, Boolean> sortMap)throws Exception;
}
