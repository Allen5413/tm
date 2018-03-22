package com.zs.service.sync.student.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.EditOnceOrderForIsSendStudentByStudentCodeDAO;
import com.zs.dao.sale.studentbookorder.EditStudentBookOrderForIsSendStudentByStudentCodeDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sale.StudentBookOrder;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.EditStudentForIsSendStudentByCodeService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/9/19.
 */
@Service("editStudentForIsSendStudentByCodeService")
public class EditStudentForIsSendStudentByCodeServiceImpl extends EntityServiceImpl<Student, StudentDAO> implements EditStudentForIsSendStudentByCodeService {

    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private EditStudentBookOrderForIsSendStudentByStudentCodeDAO editStudentBookOrderForIsSendStudentByStudentCodeDAO;
    @Resource
    private EditOnceOrderForIsSendStudentByStudentCodeDAO editOnceOrderForIsSendStudentByStudentCodeDAO;

    @Override
    @Transactional
    public void edit(String code, int isSendStudent, String address, String mobile, String postalCode, String loginName) throws Exception {
        Student student = findStudentByCodeDAO.getStudentByCode(code);
        if(null == student){
            throw new BusinessException("没有找到学号: "+code+", 的学生");
        }
        student.setIsSendStudent(isSendStudent);
        student.setSendAddress(address);
        student.setSendPhone(mobile);
        student.setSendZipCode(postalCode);
        student.setOperateTime(DateTools.getLongNowTime());
        super.update(student);

        //修改该学生的还没有分拣的订单
        editStudentBookOrderForIsSendStudentByStudentCodeDAO.edit(isSendStudent == Student.IS_SEND_STUDENT_YES ? StudentBookOrder.IS_SEND_STUDENT_YES : StudentBookOrder.IS_SEND_STUDENT_NOT, code);
        if(isSendStudent == Student.IS_SEND_STUDENT_YES) {
            editStudentBookOrderForIsSendStudentByStudentCodeDAO.editSendInfo(address, mobile, postalCode, code);
        }

        //修改该学生的还没有分拣的一次性订单
        editOnceOrderForIsSendStudentByStudentCodeDAO.edit(isSendStudent == Student.IS_SEND_STUDENT_YES ?  StudentBookOnceOrder.IS_SEND_STUDENT_YES : StudentBookOnceOrder.IS_SEND_STUDENT_NOT, code);
        if(isSendStudent == Student.IS_SEND_STUDENT_YES) {
            editOnceOrderForIsSendStudentByStudentCodeDAO.editSendInfo(address, mobile, postalCode, code);
        }
    }
}
