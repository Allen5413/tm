package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/9/4.
 */
public interface CountSendStudentBookOrderTMService extends EntityService {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
