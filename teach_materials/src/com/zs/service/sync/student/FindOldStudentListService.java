package com.zs.service.sync.student;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityService;
import com.zs.domain.sync.Student;

import java.util.List;
import java.util.Map;

/**
 * 获取旧生数据
 * Created by Allen on 2015/5/8.
 */
public interface FindOldStudentListService extends EntityService<Student> {
    public List<Student> findListByWhere(int year, int quarter)throws Exception;
}
