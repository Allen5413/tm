package com.zs.service.basic.spotgroupstudent.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.spotgroupstudent.FindSpotGroupStudentByStudentCodeDAO;
import com.zs.dao.basic.spotgroupstudent.SpotGroupStudentDAO;
import com.zs.domain.basic.SpotGroupStudent;
import com.zs.service.basic.spotgroupstudent.AddSpotGroupStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/3.
 */
@Service("addSpotGroupStudentService")
public class AddSpotGroupStudentServiceImpl
        extends EntityServiceImpl<SpotGroupStudent, SpotGroupStudentDAO>
        implements AddSpotGroupStudentService {

    @Resource
    private FindSpotGroupStudentByStudentCodeDAO findSpotGroupStudentByStudentCodeDAO;

    @Override
    public void addSpotGroupStudent(SpotGroupStudent spotGroupStudent, String loginName) throws Exception {
        if(null != spotGroupStudent){
            //查询该学号是否已经关联了学习中心
            SpotGroupStudent validSpotGroupStudent = findSpotGroupStudentByStudentCodeDAO.getSpotGroupStudentByStudentCode(spotGroupStudent.getStudentCode());
            if(null != validSpotGroupStudent && validSpotGroupStudent.getStudentCode().equals(spotGroupStudent.getStudentCode())){
                throw new BusinessException("该学号已经被分组！");
            }
            spotGroupStudent.setCreator(loginName);
            super.save(spotGroupStudent);
        }
    }
}
