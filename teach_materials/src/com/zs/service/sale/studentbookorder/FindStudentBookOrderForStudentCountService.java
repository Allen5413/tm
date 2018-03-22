package com.zs.service.sale.studentbookorder;

import  net.sf.json.JSONObject;
import com.feinno.framework.common.service.EntityService;

import java.util.List;

/**
 * Created by Allen on 2016/10/24.
 */
public interface FindStudentBookOrderForStudentCountService extends EntityService {
    public List<JSONObject> find(long semesterId, String spotCode, int state, int countNum)throws Exception;
}
