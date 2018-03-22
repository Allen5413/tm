package com.zs.service.statis;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/12/1.
 */
public interface FindSendTeachMaterialService extends EntityService {
    public List<JSONObject> findListByWhere(Map<String, String > paramsMap)throws Exception;
}
