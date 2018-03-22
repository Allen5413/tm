package com.zs.service.basic.semester;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Semester;

/**
 * 分页查询学期信息
 * 无查询条件，按照年份，季度倒排序
 * Created by LihongZ on 2015/5/11.
 */
public interface FindSemesterPageByWhereService extends EntityService<Semester> {
    /**
     * 分页查询学期信息
     * @param semesterPageInfo
     * @return
     * @throws Exception
     */
    public PageInfo<Semester> findPageBywhere(PageInfo<Semester> semesterPageInfo) throws Exception;
}
