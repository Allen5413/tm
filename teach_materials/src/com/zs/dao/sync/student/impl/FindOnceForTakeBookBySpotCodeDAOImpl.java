package com.zs.dao.sync.student.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 查询学习中心领书学生信息
 * Created by Allen on 2015/9/10.
 */
@Service("findOnceForTakeBookBySpotCodeDAO")
public class FindOnceForTakeBookBySpotCodeDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder();

        sql.append("select l.name levelName, sp.name specName, s.code, s.name, s.study_enter_year, case when s.study_quarter = 0 then '春' else '秋' end quarter, s.home_tel, s.mobile ");
        sql.append("from student_book_once_order sbo, sync_student s, sync_level l, sync_spec sp ");
        sql.append("where sbo.student_code = s.code and s.level_code = l.code and s.spec_code = sp.code ");

        String semesterId = paramsMap.get("semesterId");
        String state = paramsMap.get("state");
        String spotCode = paramsMap.get("spotCode");

        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isEmpty(state)){
            sql.append("and sbo.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            sql.append("and sbo.state >= ? ");
            params.add(Integer.parseInt(state));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and s.spot_code = ? ");
            params.add(spotCode);
        }

        if(null != sortMap) {
            sql.append("ORDER BY ");
            int i = 0;
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                if(0 < i){
                    sql.append(",");
                }
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
                i++;
            }
        }

        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), params.toArray());
        return list;
    }
}
