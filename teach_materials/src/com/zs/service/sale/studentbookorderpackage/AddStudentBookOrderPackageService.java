package com.zs.service.sale.studentbookorderpackage;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrderPackage;

/**
 * Created by Allen on 2015/7/22.
 */
public interface AddStudentBookOrderPackageService extends EntityService<StudentBookOrderPackage> {
    public StudentBookOrderPackage addStudentBookOrderPackage(String spotCode, String orderCodes, String logisticCode, int isNoce, String loginName)throws Exception;
}
