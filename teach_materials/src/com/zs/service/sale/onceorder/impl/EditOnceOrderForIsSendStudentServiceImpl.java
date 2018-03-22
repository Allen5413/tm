package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderByCodeDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.service.sale.onceorder.EditOnceOrderForIsSendStudentService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2017/4/13.
 */
@Service("editOnceOrderForIsSendStudentService")
public class EditOnceOrderForIsSendStudentServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, FindOnceOrderByCodeDAO>
        implements EditOnceOrderForIsSendStudentService {

    @Resource
    private FindOnceOrderByCodeDAO findOnceOrderByCodeDAO;

    @Override
    @Transactional
    public void edit(long id, int isSendStudent, String address, String sendPhone, String sendZipCode, String loginName) throws Exception {
        //查询订单信息
        StudentBookOnceOrder studentBookOnceOrder = findOnceOrderByCodeDAO.findOne(id);
        if(null == studentBookOnceOrder){
            throw new BusinessException("没有找到订单");
        }
        //修改状态
        if(isSendStudent == StudentBookOnceOrder.IS_SEND_STUDENT_YES) {
            studentBookOnceOrder.setIsSendStudent(StudentBookOnceOrder.IS_SEND_STUDENT_YES);
            studentBookOnceOrder.setSendPhone(sendPhone);
            studentBookOnceOrder.setSendAddress(address);
            studentBookOnceOrder.setSendZipCode(sendZipCode);
        }else{
            studentBookOnceOrder.setIsSendStudent(StudentBookOnceOrder.IS_SEND_STUDENT_NOT);
            studentBookOnceOrder.setSendPhone(null);
            studentBookOnceOrder.setSendAddress(null);
            studentBookOnceOrder.setSendZipCode(null);
        }
        studentBookOnceOrder.setOperator(loginName);
        studentBookOnceOrder.setOperateTime(DateTools.getLongNowTime());
        super.update(studentBookOnceOrder);
    }
}
