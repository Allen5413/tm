package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForStudentSignService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生领书
 * Created by Allen on 2015/9/10.
 */
@Service("editStudentBookOrderForStudentSignService")
public class EditStudentBookOrderForStudentSignServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO>
        implements EditStudentBookOrderForStudentSignService {

    @Override
    @Transactional
    public void editStudentSign(String loginName, String... ids) throws Exception {
        if(null == ids || 1 > ids.length){
            throw new BusinessException("没有传入领书的订单");
        }
        for(String id : ids){
            StudentBookOrder studentBookOrder = super.get(Long.parseLong(id));
            if(null != studentBookOrder){
                if(studentBookOrder.getState() < StudentBookOrder.STATE_SEND){
                    throw new BusinessException("订单号："+studentBookOrder.getOrderCode()+", 状态不能领书！");
                }
                studentBookOrder.setStudentSign(StudentBookOrder.STUDENTSIGN_YES);
                studentBookOrder.setOperator(loginName);
                studentBookOrder.setOperateTime(DateTools.getLongNowTime());
                super.update(studentBookOrder);
            }
        }
    }
}
