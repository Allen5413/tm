package com.zs.service.sale.studentbookordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Allen on 2015/7/19.
 */
public interface FindStudentBookOrderTMForSpotPrintService extends EntityService {
    public JSONObject findStudentBookOrderTMForSpotPrint(HttpServletRequest request, Map<String, String> paramsMap, Map<String, Boolean> sortMap)throws Exception;
}
