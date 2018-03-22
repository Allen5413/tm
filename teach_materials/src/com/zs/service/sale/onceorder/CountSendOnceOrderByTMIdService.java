package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

import java.util.Map;

/**
 * Created by Allen on 2015/9/4.
 */
public interface CountSendOnceOrderByTMIdService extends EntityService {
    public JSONArray find(Map<String, String> paramsMap, Map<String, Boolean> sortMap);
}
