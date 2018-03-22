package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;

/**
 * Created by Allen on 2015/7/22.
 */
public interface FindOnceOrderByCodeService extends EntityService<StudentBookOnceOrder> {
    public StudentBookOnceOrder find(String orderCode);
}
