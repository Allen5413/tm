package com.zs.service.statis;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

/**
 * Created by Allen on 2015/11/25 0025.
 */
public interface FindIssueChannelPayDetailService extends EntityService {
    public JSONObject findListByWhere(long semesterId, long icId, String type)throws Exception;
}
