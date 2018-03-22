package com.zs.service.sale.onceorder;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/6/28.
 */
public interface FindOrderStudentForCopyService extends EntityService {
    public List<JSONObject> find(Map<String, String> paramsMap)throws Exception;
}
