package com.zs.service.statis;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

/**
 * Created by Allen on 2015/9/23.
 */
public interface FindOrderSendService extends EntityService {
    public JSONObject findListByWhere()throws Exception;
}
