package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.service.sale.onceorder.FindOnceOrderByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/7/22.
 */
@Service("findOnceOrderByCodeService")
public class FindOnceOrderByCodeServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, FindOnceOrderByCodeDAO>
        implements FindOnceOrderByCodeService {

    @Resource
    private FindOnceOrderByCodeDAO findOnceOrderByCodeDAO;

    @Override
    public StudentBookOnceOrder find(String orderCode) {
        return findOnceOrderByCodeDAO.find(orderCode);
    }
}
