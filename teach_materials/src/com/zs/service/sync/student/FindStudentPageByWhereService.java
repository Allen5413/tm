package com.zs.service.sync.student;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;

import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
public interface FindStudentPageByWhereService extends EntityService<Student> {
    public PageInfo<Student> findPageByWhere(PageInfo<Student> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap)throws Exception;
}
