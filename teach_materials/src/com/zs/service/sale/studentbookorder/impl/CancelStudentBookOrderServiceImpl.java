package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindStudentBookOnceOrderByStudentCodeForUnConfirmDAO;
import com.zs.dao.sale.onceordertm.EditOnceOrderTMForIsNotBuyDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.dao.sale.studentbookorderlog.StudentBookOrderLogDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderLog;
import com.zs.service.sale.studentbookorder.CancelStudentBookOrderService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 取消确认学生订单
 * Created by Allen on 2015/7/16.
 */
@Service("cancelStudentBookOrderService")
public class CancelStudentBookOrderServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO> implements CancelStudentBookOrderService {

    @Resource
    private StudentBookOrderLogDAO studentBookOrderLogDAO;

    @Override
    @Transactional
    public void cancelStudentBookOrder(Long[] ids, String loginName) throws Exception {
        if(null == ids || 1 > ids.length){
            throw new BusinessException("没有传入要取消的订单");
        }
        for (Long id : ids){
            //查询订单信息
            StudentBookOrder studentBookOrder = super.get(id);
            if(null == studentBookOrder || studentBookOrder.getId() != id){
                throw new BusinessException("没有找到订单信息");
            }
            studentBookOrder.setState(StudentBookOrder.STATE_UNCONFIRMED);
            studentBookOrder.setIsStock(StudentBookOrder.ISSTOCK_YES);
            studentBookOrder.setOperator(loginName);
            studentBookOrder.setOperateTime(DateTools.getLongNowTime());
            //修改状态信息
            super.update(studentBookOrder);
            //记录订单日志信息记录
            StudentBookOrderLog studentBookOrderLog = new StudentBookOrderLog();
            studentBookOrderLog.setOrderCode(studentBookOrder.getOrderCode());
            studentBookOrderLog.setState(StudentBookOrder.STATE_UNCONFIRMED);
            studentBookOrderLog.setOperator(loginName);
            studentBookOrderLog.setOperateTime(DateTools.getLongNowTime());
            studentBookOrderLogDAO.save(studentBookOrderLog);
        }
    }
}
