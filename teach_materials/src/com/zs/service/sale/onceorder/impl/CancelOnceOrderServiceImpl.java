package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.dao.sale.onceorderlog.OnceOrderLogDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOnceOrderLog;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.CancelOnceOrderService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 取消确认学生订单
 * Created by Allen on 2015/7/16.
 */
@Service("cancelOnceOrderService")
public class CancelOnceOrderServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, FindOnceOrderByCodeDAO> implements CancelOnceOrderService {

    @Resource
    private OnceOrderLogDAO onceOrderLogDAO;

    @Override
    @Transactional
    public void cancelOnceOrder(Long[] ids, String loginName) throws Exception {
        if(null == ids || 1 > ids.length){
            throw new BusinessException("没有传入要取消的订单");
        }
        for (Long id : ids){
            //查询订单信息
            StudentBookOnceOrder studentBookOnceOrder = super.get(id);
            if(null == studentBookOnceOrder || studentBookOnceOrder.getId() != id){
                throw new BusinessException("没有找到订单信息");
            }
            studentBookOnceOrder.setState(StudentBookOnceOrder.STATE_UNCONFIRMED);
            studentBookOnceOrder.setOperator(loginName);
            studentBookOnceOrder.setOperateTime(DateTools.getLongNowTime());
            //修改状态信息
            super.update(studentBookOnceOrder);
            //记录订单日志信息记录
            StudentBookOnceOrderLog studentBookOnceOrderLog = new StudentBookOnceOrderLog();
            studentBookOnceOrderLog.setOrderId(studentBookOnceOrder.getId());
            studentBookOnceOrderLog.setState(StudentBookOrder.STATE_UNCONFIRMED);
            studentBookOnceOrderLog.setOperator(loginName);
            studentBookOnceOrderLog.setOperateTime(DateTools.getLongNowTime());
            onceOrderLogDAO.save(studentBookOnceOrderLog);
        }
    }
}
