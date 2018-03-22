package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOnceOrder;
import net.sf.json.JSONObject;

/**
 * Created by Allen on 2016/11/15.
 */
public interface CountOnceOrderForConfirmStudentService extends EntityService<StudentBookOnceOrder> {
    public JSONObject find(String spotCode, int flag);
}
