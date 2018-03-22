package com.zs.dao.finance.invoice.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询可以提供开发票的学生
 * Created by Allen on 2016/5/4 0004.
 */
@Service("findAddInvoiceStudentListDAO")
public class FindAddInvoiceStudentListDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder();

        sql.append("select tt.code, tt.name, tt.spec_code, tt.level_code, tt.pay, sum(tt.money) from (");
        sql.append("select t.*, ifnull(i.money, 0) money from (");
        sql.append("select s.code, s.name, s.spec_code, s.level_code, sum(se.pay) pay ");
        sql.append("from sync_student s, student_expense se ");
        sql.append("where s.code = se.student_code and s.spot_code = ? ");

        List<Object> params = new ArrayList<Object>();
        String spotCode = paramsMap.get("spotCode");
        String year = paramsMap.get("year");
        String quarter = paramsMap.get("quarter");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String studentCode = paramsMap.get("studentCode");
        params.add(spotCode);

        if(!StringUtils.isEmpty(year)){
            sql.append("and s.study_enter_year = ? ");
            params.add(Integer.parseInt(year));
        }
        if(!StringUtils.isEmpty(quarter)){
            sql.append("and s.study_quarter = ? ");
            params.add(Integer.parseInt(quarter));
        }
        if(!StringUtils.isEmpty(specCode)){
            sql.append("and s.spec_code = ? ");
            params.add(specCode);
        }
        if(!StringUtils.isEmpty(levelCode)){
            sql.append("and s.level_code = ? ");
            params.add(levelCode);
        }
        if(!StringUtils.isEmpty(studentCode)){
            sql.append("and s.code = ? ");
            params.add(studentCode);
        }
        sql.append("group by s.code, s.name, s.spec_code, s.level_code ");
        sql.append(") t LEFT JOIN invoice i on t.code = i.student_code ");
        sql.append("where not EXISTS(select * from invoice i where i.student_code = t.code and i.state = 0) and t.pay > 0 ");
        sql.append(") tt where tt.pay - tt.money > 0 ");
        sql.append("GROUP BY tt.code, tt.name, tt.spec_code, tt.level_code, tt.pay ORDER BY tt.code");

        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), params.toArray());
        return list;
    }
}
