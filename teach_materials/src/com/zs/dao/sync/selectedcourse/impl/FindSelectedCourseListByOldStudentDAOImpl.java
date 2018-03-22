package com.zs.dao.sync.selectedcourse.impl;

import com.feinno.framework.common.dao.jpa.JapDynamicQueryDao;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.sync.SelectedCourse;
import com.zs.domain.sync.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/8.
 */
@Service("findSelectedCourseListByOldStudentDAO")
public class FindSelectedCourseListByOldStudentDAOImpl
        extends JapDynamicQueryDao<SelectedCourse>
        implements FindListByWhereDAO {
    @Override
    public List<SelectedCourse> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder("select sc.* from SelectedCourse sc, Student s where sc.studentCode = s.code ");
        int year = Integer.parseInt(paramsMap.get("year"));
        int quarter = Integer.parseInt(paramsMap.get("quarter"));

        List<Object> param = new ArrayList<Object>();
        if(quarter == Student.AUTUMN){
            sql.append("and ((s.studyEnterYear = ? and s.studyQuarter < ?) or s.studyEnterYear < ?) ");
            param.add(year);
            param.add(quarter);
            param.add(year);
        }
        else{
            sql.append("and s.studyEnterYear < ? ");
            param.add(year);
        }
        if(null != sortMap && 0 < sortMap.size()) {
            sql.append(" order by ");
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
            }
        }

        return super.queryByJpql(sql.toString(), param.toArray());
    }
}
