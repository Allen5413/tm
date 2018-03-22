package com.zs.service.sync.student.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.FindOldStudentListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取旧生数据
 * Created by Allen on 2015/5/8.
 */
@Service("findOldStudentListService")
public class FindOldStudentListServiceImpl
        extends EntityServiceImpl<Student, StudentDAO>
        implements FindOldStudentListService{

    @Resource
    public FindListByWhereDAO findOldStudentListDAO;

    @Override
    public List<Student> findListByWhere(int year, int quarter) throws Exception {
        Map<String, String> params = new HashMap<String, String>(2);
        params.put("year", year+"");
        params.put("quarter", quarter+"");
        return findOldStudentListDAO.findListByWhere(params, null);
    }
}
