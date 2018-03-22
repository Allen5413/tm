package com.zs.service.sale.studentbookordertm;

import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/9/5.
 */
public interface EditSendStudentBookOrderTMPriceService extends EntityService {
    public void editSendStudentBookOrderTMPrice(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName)throws Exception;
}
