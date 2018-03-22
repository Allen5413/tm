package com.zs.service.sync.spec;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

/**
 * Created by Allen on 2016/10/20.
 */
public interface FindSpecCoursePageService extends EntityService {
    public PageInfo findPageByWhere(PageInfo pageInfo)throws Exception;
}
