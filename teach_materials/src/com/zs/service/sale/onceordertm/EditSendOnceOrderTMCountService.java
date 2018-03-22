package com.zs.service.sale.onceordertm;

import java.util.Map;

/**
 * Created by Allen on 2015/12/21.
 */
public interface EditSendOnceOrderTMCountService {
    public void editor(Map<String, String> paramsMap, Map<String, Boolean> sortMap, String loginName)throws Exception;
}
