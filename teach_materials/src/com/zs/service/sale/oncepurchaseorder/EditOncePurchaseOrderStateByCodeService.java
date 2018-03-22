package com.zs.service.sale.oncepurchaseorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.OncePurchaseOrder;

/**
 * Created by Allen on 2016/6/22.
 */
public interface EditOncePurchaseOrderStateByCodeService extends EntityService<OncePurchaseOrder> {
    public void editor(String id, String loginName)throws Exception;
}
