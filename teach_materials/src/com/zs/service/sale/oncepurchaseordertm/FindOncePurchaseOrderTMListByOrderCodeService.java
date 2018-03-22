package com.zs.service.sale.oncepurchaseordertm;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

/**
 * 订单入库时，查询订单下的教材明细
 * Created by Allen on 2015/5/12.
 */
public interface FindOncePurchaseOrderTMListByOrderCodeService extends EntityService {
    public JSONArray find(String orderCode, Long semesterId);
}
