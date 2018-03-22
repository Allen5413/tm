package com.zs.service.finance.invoice;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by Allen on 2016/5/4 0004.
 */
public interface FindInvoiceByWhereService extends EntityService {
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map)throws Exception;
}
