package com.zs.service.sync.student;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;

/**
 * 修改学生的订单是否直接邮寄给学生
 * Created by Allen on 2016/9/19.
 */
public interface EditStudentForIsSendStudentByCodeService extends EntityService<Student> {
    public void edit(String code, int isSendStudent, String address, String mobile, String postalCode, String loginName)throws Exception;
}
