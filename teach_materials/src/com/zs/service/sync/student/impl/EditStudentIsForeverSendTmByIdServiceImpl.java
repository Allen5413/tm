package com.zs.service.sync.student.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.EditStudentIsForeverSendTmByIdService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2016/5/24.
 */
@Service("editStudentIsForeverSendTmByIdService")
public class EditStudentIsForeverSendTmByIdServiceImpl extends EntityServiceImpl<Student, StudentDAO> implements EditStudentIsForeverSendTmByIdService {

    @Override
    @Transactional
    public void edit(long id, int isForeverSendTm, String loginName) throws Exception {
        Student student = super.get(id);
        if(null != student){
            student.setIsForeverSnedTm(isForeverSendTm);
            student.setOperateTime(DateTools.getLongNowTime());
            super.update(student);
        }
    }
}
