package com.zs.service.finance.spotexpenseoth;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by Allen on 2016/3/25.
 */
public interface FindSpotExpenseOthForCountService extends EntityService {
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map)throws Exception;
}
