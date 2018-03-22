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
 * 查询有余额的学生信息
 * Created by Allen on 2016/1/4 0004.
 */
@Service("findStudentForFinanceAccListDAO")
public class FindStudentForFinanceAccListDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {

    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select s.code, s.name, s.spec_code, s.level_code, IFNULL(sum(se.pay),0) pay, IFNULL(sum(se.buy), 0) buy, IFNULL(sum(i.money), 0) money ");
        sql.append("FROM sync_student s INNER JOIN student_expense se on s. CODE = se.student_code LEFT JOIN invoice i on s.`code` = i.student_code and i. state = 1 ");
        sql.append("where 1=1 ");

        String spotCode = paramsMap.get("spotCode");
        String enterYear = paramsMap.get("enterYear");
        String enterQuarter = paramsMap.get("enterQuarter");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String state = paramsMap.get("state");
        String studentCodes = paramsMap.get("studentCodes");

        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and s.spot_code = ? ");
            params.add(spotCode);
        }
        if(!StringUtils.isEmpty(enterYear)){
            sql.append("and s.study_enter_year = ? ");
            params.add(Integer.parseInt(enterYear));
        }
        if(!StringUtils.isEmpty(enterQuarter)){
            sql.append("and s.study_quarter = ? ");
            params.add(Integer.parseInt(enterQuarter));
        }
        if(!StringUtils.isEmpty(state)){
            sql.append("and s.state = ? ");
            params.add(Integer.parseInt(state));
        }
        if(!StringUtils.isEmpty(specCode)){
            sql.append("and s.spec_code = ? ");
            params.add(specCode);
        }
        if(!StringUtils.isEmpty(levelCode)){
            sql.append("and s.level_code = ? ");
            params.add(levelCode);
        }
        if(!StringUtils.isEmpty(studentCodes)){
            String[] studentCodeArray = studentCodes.split(",");
            if(null != studentCodeArray && 0 < studentCodeArray.length){
                sql.append("and s.code in (");
                for(int i=0; i<studentCodeArray.length; i++){
                    if(i == studentCodeArray.length - 1){
                        sql.append("?) ");
                    }else{
                        sql.append("?,");
                    }
                    params.add(studentCodeArray[i]);
                }
            }
        }

        sql.append("group by code) t where t.pay > t.buy ");

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
