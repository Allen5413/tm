package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2016/10/24.
 */
public interface FindOnceOrderForStudentCountService extends EntityService {
    public List<JSONObject> find(long semesterId, int state, int countNum)throws Exception;
}
