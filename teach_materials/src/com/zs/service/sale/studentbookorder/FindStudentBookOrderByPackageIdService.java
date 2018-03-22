package com.zs.service.sale.studentbookorder;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sale.StudentBookOrder;

import java.util.List;

/**
 * Created by Allen on 2015/7/25.
 */
public interface FindStudentBookOrderByPackageIdService extends EntityService<StudentBookOrder> {
    public List<StudentBookOrder> findStudentBookOrderByPackageId(long packageId);
}
