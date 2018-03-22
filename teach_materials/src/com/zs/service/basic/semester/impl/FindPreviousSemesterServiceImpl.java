package com.zs.service.basic.semester.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.dao.basic.semester.FindPreviousSemesterDao;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindPreviousSemesterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现查询上一学期的接口
 * Created by LihongZhang on 2015/5/12.
 */
@Service("findPreviousSemesterService")
public class FindPreviousSemesterServiceImpl extends EntityServiceImpl<Semester,FindNowSemesterDAO> implements FindPreviousSemesterService {

    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Resource
    private FindPreviousSemesterDao findPreviousSemesterDao;

    @Override
    public Semester getPreviousSemester() throws Exception {
        Semester semester = findNowSemesterDAO.getNowSemester();
        if (null != semester){
            if (semester.getQuarter()==Semester.QUARTER_AUTUMN){
                return findPreviousSemesterDao.getPreviousSemester(semester.getYear(),Semester.QUARTER_SPRING);
            }else if(semester.getQuarter()== Semester.QUARTER_SPRING){
                return findPreviousSemesterDao.getPreviousSemester(semester.getYear()-1,Semester.QUARTER_AUTUMN);
            }
            else {
                throw new BusinessException("季度类型错误");
            }
        }else {
            throw new BusinessException("当前学期未设置");
        }
    }
}
