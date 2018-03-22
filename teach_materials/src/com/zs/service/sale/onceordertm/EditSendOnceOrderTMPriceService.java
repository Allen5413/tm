package com.zs.service.sale.onceordertm;

import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/9/5.
 */
public interface EditSendOnceOrderTMPriceService extends EntityService {
    public void editor(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName)throws Exception;
}
