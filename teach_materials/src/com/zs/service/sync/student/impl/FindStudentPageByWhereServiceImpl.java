package com.zs.service.sync.student.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.sync.Student;
import com.zs.service.sync.student.FindStudentPageByWhereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Service("findStudentPageByWhereService")
public class FindStudentPageByWhereServiceImpl extends EntityServiceImpl<Student, StudentDAO>
        implements FindStudentPageByWhereService {

    @Resource
    public FindPageByWhereDAO findStudentPageByWhereDAO;

    @Override
    @Transactional
    public PageInfo<Student> findPageByWhere(PageInfo<Student> pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        return findStudentPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
    }
}
