package com.zs.service.basic.semester;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Semester;

/**
 * 查询上一学期
 * Created by Lihongzhang on 2015/5/12.
 */
public interface FindPreviousSemesterService extends EntityService<Semester> {

    /**
     * 获取上一学期的方法
     * @return
     * @throws Exception
     */
    public Semester getPreviousSemester() throws Exception;
}
