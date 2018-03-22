package com.zs.service.placeorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.placeorder.FindPlaceOrderByCodeDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.FindPlaceOrderByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/12/9 0009.
 */
@Service("findPlaceOrderByCodeService")
public class FindPlaceOrderByCodeServiceImpl extends EntityServiceImpl implements FindPlaceOrderByCodeService {

    @Resource
    private FindPlaceOrderByCodeDAO findPlaceOrderByCodeDAO;

    @Override
    public TeachMaterialPlaceOrder findByCode(String orderCode) throws Exception {
        return findPlaceOrderByCodeDAO.findPlaceOrderByCode(orderCode);
    }
}
