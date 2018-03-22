package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;

/**
 * Created by Allen on 2015/9/10.
 */
public interface EditOnceOrderForStudentSignService extends EntityService<StudentBookOnceOrder> {
    public void editor(String loginName, String... ids)throws Exception;
}
