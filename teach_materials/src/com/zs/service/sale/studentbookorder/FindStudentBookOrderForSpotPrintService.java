package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

import java.util.Map;

/**
 * Created by Allen on 2015/7/18.
 */
public interface FindStudentBookOrderForSpotPrintService extends EntityService {
    public JSONArray findStudentBookOrderForSpotPrint(Map<String, String> paramsMap, Map<String, Boolean> sortMap);
}
