package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;

/**
 * Created by Allen on 2017/4/13.
 */
public interface EditOnceOrderForIsSendStudentService extends EntityService<StudentBookOnceOrder> {
    public void edit(long id, int isSendStudent, String address, String sendPhone, String sendZipCode, String loginName)throws Exception;
}
