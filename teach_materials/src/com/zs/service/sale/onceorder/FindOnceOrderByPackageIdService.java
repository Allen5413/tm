package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;

import java.util.List;

/**
 * Created by Allen on 2015/7/25.
 */
public interface FindOnceOrderByPackageIdService extends EntityService<StudentBookOnceOrder> {
    public List<StudentBookOnceOrder> find(long packageId);
}
