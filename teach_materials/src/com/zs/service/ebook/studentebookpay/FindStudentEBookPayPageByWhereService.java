package com.zs.service.ebook.studentebookpay;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by Allen on 2016/1/4.
 */
public interface FindStudentEBookPayPageByWhereService extends EntityService {
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
