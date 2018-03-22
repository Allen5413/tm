package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;

import java.sql.Timestamp;

/**
 * Created by Allen on 2015/7/23.
 */
public interface EditOnceOrderForStateService extends EntityService<StudentBookOnceOrder> {
    public void editor(String orderCode, Long packageId, int state, Timestamp operateTime, String loginName)throws Exception;
}
