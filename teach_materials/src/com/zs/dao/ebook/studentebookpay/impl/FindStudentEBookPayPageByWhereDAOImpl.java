package com.zs.dao.ebook.studentebookpay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/4.
 */
@Service("findStudentEBookPayPageByWhereDAO")
public class FindStudentEBookPayPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        String field = "sep.id, s.code, s.name, sp.code spCode, sp.name spName, ROUND(sep.money/100, 2), sep.arrival_time, sep.pay_type, sep.remark, s.spec_code, s.level_code";
        StringBuilder sql = new StringBuilder("FROM student_ebook_pay sep, sync_student s, sync_spot sp ");
        sql.append("where sep.student_code = s.code and s.spot_code = sp.code ");

        String spotCode = paramsMap.get("spotCode");
        String year = paramsMap.get("year");
        String quarter = paramsMap.get("quarter");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String type = paramsMap.get("type");
        String studentCode = paramsMap.get("studentCode");
        String name = paramsMap.get("name");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and sp.code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(year)){
            sql.append(" and s.study_enter_year = ? ");
            param.add(Integer.parseInt(year));
        }
        if(!StringUtils.isEmpty(quarter)){
            sql.append(" and s.study_quarter = ? ");
            param.add(Integer.parseInt(quarter));
        }
        if(!StringUtils.isEmpty(specCode)){
            sql.append(" and s.spec_code = ? ");
            param.add(specCode);
        }
        if(!StringUtils.isEmpty(levelCode)){
            sql.append(" and s.level_code = ? ");
            param.add(levelCode);
        }
        if(!StringUtils.isEmpty(type)){
            sql.append(" and sep.pay_type = ? ");
            param.add(Integer.parseInt(type));
        }
        if(!StringUtils.isEmpty(studentCode)){
            sql.append(" and s.code = ? ");
            param.add(studentCode);
        }
        if(!StringUtils.isEmpty(name)){
            sql.append(" and s.name like ? ");
            param.add("%"+name+"%");
        }
        if(null != sortMap) {
            sql.append("order by ");
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
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
