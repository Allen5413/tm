package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderByPackageIdDAO;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByPackageIdDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.FindOnceOrderByPackageIdService;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByPackageIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2015/7/25.
 */
@Service("findOnceOrderByPackageIdService")
public class FindOnceOrderByPackageIdServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, FindOnceOrderByPackageIdDAO>
    implements FindOnceOrderByPackageIdService{

    @Resource
    private FindOnceOrderByPackageIdDAO findOnceOrderByPackageIdDAO;

    @Override
    public List<StudentBookOnceOrder> find(long packageId) {
        return findOnceOrderByPackageIdDAO.find(packageId);
    }
}
