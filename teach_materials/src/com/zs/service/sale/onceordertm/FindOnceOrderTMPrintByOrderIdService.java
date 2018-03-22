package com.zs.service.sale.onceordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

/**
 * Created by Allen on 2015/9/9.
 */
public interface FindOnceOrderTMPrintByOrderIdService extends EntityService {
    public JSONObject find(String... ids)throws Exception;

    public void editPrintSort(String... ids)throws Exception;
}
