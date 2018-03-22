package com.zs.service.basic.semester.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.basic.semester.SemesterDAO;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.semester.FindSemesterPageByWhereService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现分页查询学期信息接口
 * Created by LihongZhang on 2015/5/11.
 */
@Service("findSemesterPageByWhereService")
public class FindSemesterPageByWhereServiceImpl extends EntityServiceImpl<Semester,SemesterDAO> implements FindSemesterPageByWhereService{

    @Resource
    private FindPageByWhereDAO findSemesterPageByWhereDao;

    @Override
    public PageInfo<Semester> findPageBywhere(PageInfo<Semester> semesterPageInfo) throws Exception {
        return findSemesterPageByWhereDao.findPageByWhere(semesterPageInfo,null,null);
    }
}
