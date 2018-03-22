package com.zs.service.basic.semester.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindNowSemesterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2015/5/23.
 */
@Service("findNowSemesterService")
public class FindNowSemesterServiceImpl extends EntityServiceImpl<Semester, FindNowSemesterDAO>
    implements FindNowSemesterService {

    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    public Semester getNowSemester() {
        return findNowSemesterDAO.getNowSemester();
    }
}
