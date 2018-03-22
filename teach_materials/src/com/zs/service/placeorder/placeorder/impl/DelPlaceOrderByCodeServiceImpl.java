package com.zs.service.placeorder.placeorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.placeorder.placeorder.DelPlaceOrderByCodeDAO;
import com.zs.dao.placeorder.placeorderlog.DelPlaceOrderLogByOrderIdDAO;
import com.zs.dao.placeorder.placeordertm.DelPlaceOrderTMByOrderIdDAO;
import com.zs.domain.placeorder.TeachMaterialPlaceOrder;
import com.zs.service.placeorder.placeorder.DelPlaceOrderByCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/3/28.
 */
@Service("delPlaceOrderByCodeService")
public class DelPlaceOrderByCodeServiceImpl extends EntityServiceImpl<TeachMaterialPlaceOrder, DelPlaceOrderByCodeDAO> implements DelPlaceOrderByCodeService {

    @Resource
    private DelPlaceOrderTMByOrderIdDAO delPlaceOrderTMByOrderIdDAO;
    @Resource
    private DelPlaceOrderLogByOrderIdDAO delPlaceOrderLogByOrderIdDAO;

    @Transactional
    @Override
    public void del(Long... ids) throws Exception {
        if(null == ids || 0 >= ids.length){
            throw new BusinessException("请传入要删除的订单");
        }
        for(Long id : ids){
            TeachMaterialPlaceOrder teachMaterialPlaceOrder = super.get(id);
            if(null != teachMaterialPlaceOrder){
                //删除教材明细
                delPlaceOrderTMByOrderIdDAO.del(id);
                //删除订单日志
                delPlaceOrderLogByOrderIdDAO.del(id);
                //删除订单本身
                super.remove(teachMaterialPlaceOrder);
            }
        }
    }
}
