package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByCodeDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.FindStudentBookOrderByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/7/22.
 */
@Service("findStudentBookOrderByCodeService")
public class FindStudentBookOrderByCodeServiceImpl extends EntityServiceImpl<StudentBookOrder, FindStudentBookOrderByCodeDAO>
        implements FindStudentBookOrderByCodeService {

    @Resource
    private FindStudentBookOrderByCodeDAO findStudentBookOrderByCodeDAO;

    @Override
    public StudentBookOrder findStudentBookOrderByCode(String orderCode) {
        return findStudentBookOrderByCodeDAO.findStudentBookOrderByCode(orderCode);
    }
}
