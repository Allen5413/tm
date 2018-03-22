package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByCodeDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.dao.sale.studentbookorderlog.StudentBookOrderLogDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sale.StudentBookOrderLog;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForStateService;
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
@Service("editStudentBookOrderForStateService")
public class EditStudentBookOrderForStateServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO>
    implements EditStudentBookOrderForStateService{

    @Resource
    private FindStudentBookOrderByCodeDAO findStudentBookOrderByCodeDAO;
    @Resource
    private StudentBookOrderLogDAO studentBookOrderLogDAO;

    @Override
    @Transactional
    public void editStudentBookOrderForState(String orderCode, Long packageId, int state, Timestamp operateTime, String loginName) throws Exception {
        if(StringUtils.isEmpty(orderCode)){
            throw new BusinessException("没有传入订单号");
        }
        //查询订单信息
        StudentBookOrder studentBookOrder = findStudentBookOrderByCodeDAO.findStudentBookOrderByCode(orderCode);
        if(null == studentBookOrder){
            throw new BusinessException("没有找到["+orderCode+"]该订单");
        }
        //修改状态
        studentBookOrder.setState(state);
        if(null != packageId){
            studentBookOrder.setPackageId(packageId);
        }
        studentBookOrder.setOperator(loginName);
        if(null == operateTime) {
            studentBookOrder.setOperateTime(DateTools.getLongNowTime());
        }else{
            studentBookOrder.setOperateTime(operateTime);
        }
        super.update(studentBookOrder);

        //记录状态变更日志
        StudentBookOrderLog studentBookOrderLog = new StudentBookOrderLog();
        studentBookOrderLog.setOrderCode(orderCode);
        studentBookOrderLog.setState(state);
        studentBookOrderLog.setOperator(loginName);
        studentBookOrderLogDAO.save(studentBookOrderLog);
    }
}
