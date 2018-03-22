package com.zs.service.sale.studentbookorderpackage;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrderPackage;

/**
 * Created by Allen on 2015/7/27.
 */
public interface SendStudentBookOrderPackageService extends EntityService<StudentBookOrderPackage> {
    public void sendStudentBookOrderPackage(Long[] ids, String[] logisticCodes, String loginName)throws Exception;
}
