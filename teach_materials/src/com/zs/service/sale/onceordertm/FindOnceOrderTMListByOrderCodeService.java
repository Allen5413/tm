package com.zs.service.sale.onceordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * Created by Allen on 2015/5/26.
 */
public interface FindOnceOrderTMListByOrderCodeService extends EntityService {
    public JSONArray find(long orderId);
}
