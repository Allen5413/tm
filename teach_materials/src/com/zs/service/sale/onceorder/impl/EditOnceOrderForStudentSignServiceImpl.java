package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.onceorder.EditOnceOrderForStudentSignService;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForStudentSignService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生领书
 * Created by Allen on 2015/9/10.
 */
@Service("editOnceOrderForStudentSignService")
public class EditOnceOrderForStudentSignServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, FindOnceOrderByCodeDAO>
        implements EditOnceOrderForStudentSignService {

    @Override
    @Transactional
    public void editor(String loginName, String... ids) throws Exception {
        if(null == ids || 1 > ids.length){
            throw new BusinessException("没有传入领书的订单");
        }
        for(String id : ids){
            StudentBookOnceOrder studentBookOnceOrder = super.get(Long.parseLong(id));
            if(null != studentBookOnceOrder){
                if(studentBookOnceOrder.getState() < StudentBookOnceOrder.STATE_SEND){
                    throw new BusinessException("订单号："+studentBookOnceOrder.getOrderCode()+", 状态不能领书！");
                }
                studentBookOnceOrder.setStudentSign(StudentBookOnceOrder.STUDENTSIGN_YES);
                studentBookOnceOrder.setOperator(loginName);
                studentBookOnceOrder.setOperateTime(DateTools.getLongNowTime());
                super.update(studentBookOnceOrder);
            }
        }
    }
}
