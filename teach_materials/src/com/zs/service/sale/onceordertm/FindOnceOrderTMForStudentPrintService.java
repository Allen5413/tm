package com.zs.service.sale.onceordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/10/24.
 */
public interface FindOnceOrderTMForStudentPrintService extends EntityService {
    public JSONObject print(long semesterId, int state, int pageNum, int countNum, String operateTime, HttpServletRequest request)throws Exception;
}
