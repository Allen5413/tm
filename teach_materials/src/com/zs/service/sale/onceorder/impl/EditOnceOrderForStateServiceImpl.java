package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.dao.sale.onceorderlog.OnceOrderLogDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOnceOrderLog;
import com.zs.service.sale.onceorder.EditOnceOrderForStateService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * 修改学生订单状态，记录状态变更日志
 * Created by Allen on 2015/7/23.
 */
@Service("editOnceOrderForStateService")
public class EditOnceOrderForStateServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, FindOnceOrderByCodeDAO>
    implements EditOnceOrderForStateService{

    @Resource
    private FindOnceOrderByCodeDAO findOnceOrderByCodeDAO;
    @Resource
    private OnceOrderLogDAO onceOrderLogDAO;

    @Override
    @Transactional
    public void editor(String orderCode, Long packageId, int state, Timestamp operateTime, String loginName) throws Exception {
        if(StringUtils.isEmpty(orderCode)){
            throw new BusinessException("没有传入订单号");
        }
        //查询订单信息
        StudentBookOnceOrder studentBookOnceOrder = findOnceOrderByCodeDAO.find(orderCode);
        if(null == studentBookOnceOrder){
            throw new BusinessException("没有找到["+orderCode+"]该订单");
        }
        //修改状态
        studentBookOnceOrder.setState(state);
        if(null != packageId){
            studentBookOnceOrder.setPackageId(packageId);
        }
        studentBookOnceOrder.setOperator(loginName);
        if(null == operateTime) {
            studentBookOnceOrder.setOperateTime(DateTools.getLongNowTime());
        }else{
            studentBookOnceOrder.setOperateTime(operateTime);
        }
        super.update(studentBookOnceOrder);

        //记录状态变更日志
        StudentBookOnceOrderLog studentBookOnceOrderLog = new StudentBookOnceOrderLog();
        studentBookOnceOrderLog.setOrderId(studentBookOnceOrder.getId());
        studentBookOnceOrderLog.setState(state);
        studentBookOnceOrderLog.setOperator(loginName);
        onceOrderLogDAO.save(studentBookOnceOrderLog);
    }
}
