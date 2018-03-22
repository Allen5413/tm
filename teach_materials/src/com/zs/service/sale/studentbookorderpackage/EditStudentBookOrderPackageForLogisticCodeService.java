package com.zs.service.sale.studentbookorderpackage;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrderPackage;

/**
 * Created by Allen on 2016/3/23.
 */
public interface EditStudentBookOrderPackageForLogisticCodeService extends EntityService<StudentBookOrderPackage> {
    public void edit(long id, String logisticCodes, String loginName);
}
