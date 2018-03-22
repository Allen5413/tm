package com.zs.service.placeorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by Allen on 2016/12/19.
 */
public interface CountSpecAllOrderService extends EntityService {
    public JSONObject find(Map<String, String> paramsMap);
}
