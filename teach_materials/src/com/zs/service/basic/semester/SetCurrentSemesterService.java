package com.zs.service.basic.semester;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.Semester;

/**
 * 设置学期为当前学期
 * Created by Lihong on 2015/5/11.
 */
public interface SetCurrentSemesterService extends EntityService<Semester> {
    /**
     *  设置当前学期的方法
     * @param id 将要设置为当前学期的id
     * @param loginName
     * @throws Exception
     */
    public void setCurrentSemester(String loginName, Long id) throws Exception;
}
