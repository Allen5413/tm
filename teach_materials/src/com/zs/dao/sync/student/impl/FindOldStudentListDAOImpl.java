package com.zs.dao.sync.student.impl;

import com.feinno.framework.common.dao.jpa.JapDynamicQueryDao;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.sync.Student;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 查询旧生数据
 * Created by Allen on 2015/5/7.
 */
@Service("findOldStudentListDAO")
public class FindOldStudentListDAOImpl extends JapDynamicQueryDao<Student>
        implements FindListByWhereDAO {
    @Override
    public List<Student> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder("FROM Student where 1=1 ");
        int year = Integer.parseInt(paramsMap.get("year"));
        int quarter = Integer.parseInt(paramsMap.get("quarter"));

        List<Object> param = new ArrayList<Object>();
        if(quarter == Student.AUTUMN){
            sql.append("and ((studyEnterYear = ? and studyQuarter < ?) or studyEnterYear < ?) ");
            param.add(year);
            param.add(quarter);
            param.add(year);
        }
        else{
            sql.append("and studyEnterYear < ? ");
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
