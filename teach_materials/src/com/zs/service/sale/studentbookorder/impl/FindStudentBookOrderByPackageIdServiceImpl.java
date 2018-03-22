package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByPackageIdDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByPackageIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/7/25.
 */
@Service("findStudentBookOrderByPackageIdService")
public class FindStudentBookOrderByPackageIdServiceImpl extends EntityServiceImpl<StudentBookOrder, FindStudentBookOrderByPackageIdDAO>
    implements FindStudentBookOrderByPackageIdService{

    @Resource
    private FindStudentBookOrderByPackageIdDAO findStudentBookOrderByPackageIdDAO;

    @Override
    public List<StudentBookOrder> findStudentBookOrderByPackageId(long packageId) {
        return findStudentBookOrderByPackageIdDAO.findStudentBookOrderByPackageId(packageId);
    }
}
