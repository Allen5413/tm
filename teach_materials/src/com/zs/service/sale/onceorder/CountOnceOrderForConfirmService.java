package com.zs.service.sale.onceorder;

import net.sf.json.JSONObject;
import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/9/4.
 */
public interface CountOnceOrderForConfirmService extends EntityService {
    public JSONObject find(Map<String, String> paramsMap);
}
