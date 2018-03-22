package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/5 0005.
 */
public interface FindStudentForFinanceAccListService extends EntityService {
    public JSONArray find(Map<String, String> paramsMap, Map<String, Boolean> sortMap)throws Exception;
}

