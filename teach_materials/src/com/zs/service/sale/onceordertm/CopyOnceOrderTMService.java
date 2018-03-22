package com.zs.service.sale.onceordertm;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrderTM;

/**
 * Created by Allen on 2016/6/28.
 */
public interface CopyOnceOrderTMService extends EntityService<StudentBookOnceOrderTM> {
    public void copy(long id, String[] ids, String loginName)throws Exception;
}
