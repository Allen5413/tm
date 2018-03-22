package com.zs.service.sale.studentbookorderpackage;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrderPackage;

import java.util.Map;

/**
 * Created by Allen on 2015/7/22.
 */
public interface FindStudentBookOrderPackagePageByWhereService extends EntityService<StudentBookOrderPackage> {
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
