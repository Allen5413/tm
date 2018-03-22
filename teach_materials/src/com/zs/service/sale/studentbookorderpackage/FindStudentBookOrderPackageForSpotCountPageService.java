package com.zs.service.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;

import java.util.Map;

/**
 * Created by Allen on 2015/7/27.
 */
public interface FindStudentBookOrderPackageForSpotCountPageService extends EntityService {
    public PageInfo findStudentBookOrderPackageByNotSend(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap);
}
