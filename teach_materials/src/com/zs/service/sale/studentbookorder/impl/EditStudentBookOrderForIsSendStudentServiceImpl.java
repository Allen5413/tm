package com.zs.service.sale.studentbookorder.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.studentbookorder.FindStudentBookOrderByCodeDAO;
import com.zs.dao.sale.studentbookorder.StudentBookOrderDAO;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.service.sale.studentbookorder.EditStudentBookOrderForIsSendStudentService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2017/4/13.
 */
@Service("editStudentBookOrderForIsSendStudentService")
public class EditStudentBookOrderForIsSendStudentServiceImpl extends EntityServiceImpl<StudentBookOrder, StudentBookOrderDAO>
        implements EditStudentBookOrderForIsSendStudentService {

    @Resource
    private FindStudentBookOrderByCodeDAO findStudentBookOrderByCodeDAO;

    @Override
    @Transactional
    public void edit(String orderCode, int isSendStudent, String address, String sendPhone, String sendZipCode, String loginName) throws Exception {
        if(StringUtils.isEmpty(orderCode)){
            throw new BusinessException("没有传入订单号");
        }
        //查询订单信息
        StudentBookOrder studentBookOrder = findStudentBookOrderByCodeDAO.findStudentBookOrderByCode(orderCode);
        if(null == studentBookOrder){
            throw new BusinessException("没有找到["+orderCode+"]该订单");
        }
        //修改状态
        if(isSendStudent == StudentBookOrder.IS_SEND_STUDENT_YES) {
            studentBookOrder.setIsSendStudent(StudentBookOrder.IS_SEND_STUDENT_YES);
            studentBookOrder.setSendPhone(sendPhone);
            studentBookOrder.setSendAddress(address);
            studentBookOrder.setSendZipCode(sendZipCode);
        }else{
            studentBookOrder.setIsSendStudent(StudentBookOrder.IS_SEND_STUDENT_NOT);
            studentBookOrder.setSendPhone(null);
            studentBookOrder.setSendAddress(null);
            studentBookOrder.setSendZipCode(null);
        }
        studentBookOrder.setOperator(loginName);
        studentBookOrder.setOperateTime(DateTools.getLongNowTime());
        super.update(studentBookOrder);
    }
}
