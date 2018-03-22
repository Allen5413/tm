package com.zs.service.sale.oncepurchaseorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.OncePurchaseOrder;

import java.util.Map;

/**
 * Created by Allen on 2015/5/5.
 */
public interface FindOncePurchaseOrderPageByWhereService extends EntityService<OncePurchaseOrder> {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
