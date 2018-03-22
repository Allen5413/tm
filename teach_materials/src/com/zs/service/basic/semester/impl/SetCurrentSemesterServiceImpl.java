package com.zs.service.basic.semester.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.SetCurrentSemesterDao;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.SetCurrentSemesterService;
import com.zs.tools.DateTools;
import com.zs.tools.UserTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 实现了修改当前学期的接口
 * Created by LihongZhang on 2015/5/11.
 */
@Service("setCurrentSemesterService")
public class SetCurrentSemesterServiceImpl extends EntityServiceImpl<Semester,SetCurrentSemesterDao> implements SetCurrentSemesterService {

    @Resource
    private SetCurrentSemesterDao setCurrentSemesterDao;

    @Override
    @Transactional
    public void setCurrentSemester(String loginName,Long id) throws Exception {
        //验证学期信息是否存在
        if (null != setCurrentSemesterDao.get(id)){
            Date date = DateTools.getLongNowTime();
            //把当前学期设置为上学期
            setCurrentSemesterDao.setSemesterNotNowSemester(Semester.ISNOWSEMESTER_NOT,loginName,date,Semester.ISNOWSEMESTER_YES);
            //根据id设置当前学期
            setCurrentSemesterDao.setSemesterIsNowSemester(Semester.ISNOWSEMESTER_YES,loginName,date,id);
        }else {
            throw new BusinessException("学期信息不存在");
        }
    }
}
