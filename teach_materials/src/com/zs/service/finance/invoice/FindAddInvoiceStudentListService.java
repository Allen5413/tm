package com.zs.service.finance.invoice;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

import java.util.Map;

/**
 * Created by Allen on 2016/5/4 0004.
 */
public interface FindAddInvoiceStudentListService extends EntityService {
    public JSONArray find(Map<String, String> map);
}
