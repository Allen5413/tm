package com.zs.dao.sale.onceorder.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/4.
 */
@Service("findOrderStudentForCopyDAO")
public class FindOrderStudentForCopyDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String year = paramsMap.get("year");
        String quarter = paramsMap.get("quarter");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String spotCode = paramsMap.get("spotCode");
        String studentCode = paramsMap.get("studentCode");
        String name = paramsMap.get("name");

        StringBuilder sql = new StringBuilder();
        sql.append("select sbo.id, s.code, s.name from student_book_once_order sbo, sync_student s ");
        sql.append("where sbo.student_code = s.code and sbo.state = 0 ");

        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and sbo.semester_id = ? ");
            param.add(semesterId);
        }
        if(!StringUtils.isEmpty(year)){
            sql.append("and s.study_enter_year = ? ");
            param.add(Integer.parseInt(year));
        }
        if(!StringUtils.isEmpty(quarter)){
            sql.append("and s.study_quarter = ? ");
            param.add(Integer.parseInt(quarter));
        }
        if(!StringUtils.isEmpty(specCode)){
            sql.append("and s.spec_code = ? ");
            param.add(specCode);
        }
        if(!StringUtils.isEmpty(levelCode)){
            sql.append("and s.level_code = ? ");
            param.add(levelCode);
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and s.spot_Code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(studentCode)){
            sql.append("and s.code = ? ");
            param.add(studentCode);
        }
        if(!StringUtils.isEmpty(name)){
            sql.append("and s.name like ? ");
            param.add("%"+name+"%");
        }

        sql.append("order by s.code");
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
