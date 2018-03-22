package com.zs.dao.finance.studentexpense.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.finance.StudentExpense;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 学生费用分页查询接口的实现类
 * Created by LihongZhang on 2015/5/15.
 */
@Service("findStuEPageByWhereDao")
public class FindStuEPageByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO{

    /**
     * 没查询条件时，按照创建时间排倒序，
     * 查询条件有精确查找，按照学生学号，
     * 还有状态查找
     * @param pageInfo
     * @param paramsMap
     * @param sortMap
     * @return
     */
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo studentExpensePageInfo = new PageInfo();
        studentExpensePageInfo.setCurrentPage(pageInfo.getCurrentPage());
        studentExpensePageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        String field = "'-' as provCode, '-' as provName, sp.code as spotCode, sp.name as spotName, s.code, s.name, s.spec_code, s.level_code, sum(ifnull(se.pay,0)), sum(ifnull(se.buy,0))";
        //StringBuilder sql = new StringBuilder("FROM student_expense se left join sync_student s on se.student_code = s.code left join sync_spot sp on s.spot_code = sp.code left join sync_spot_province ssp on sp.code = ssp.spot_code left join sync_province p on p.code = ssp.province_code where 1 = 1");
        StringBuilder sql = new StringBuilder("FROM student_expense se left join sync_student s on se.student_code = s.code left join sync_spot sp on s.spot_code = sp.code where 1 = 1");
        
        String provCode = paramsMap.get("provCode");
        String spotCode = paramsMap.get("spotCode");
        String studentCode = paramsMap.get("studentCode");
        String name = paramsMap.get("name");
        String state = paramsMap.get("state");
        String semesterId = paramsMap.get("semesterId");
        String enterYear = paramsMap.get("enterYear");
        String enterQuarter = paramsMap.get("enterQuarter");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(semesterId)){
            sql.append(" and se.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
//        if(!StringUtils.isEmpty(provCode)){
//            sql.append(" and p.code = ? ");
//            param.add(provCode);
//        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and s.spot_code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(studentCode)){
            sql.append(" and s.code = ? ");
            param.add(studentCode);
        }
        if(!StringUtils.isEmpty(name)){
            sql.append(" and s.name like ? ");
            param.add("%"+name+"%");
        }
        if(!StringUtils.isEmpty(enterYear)){
            sql.append(" and s.study_enter_year = ? ");
            param.add(Integer.parseInt(enterYear));
        }
        if(!StringUtils.isEmpty(enterQuarter)){
            sql.append(" and s.study_quarter = ? ");
            param.add(Integer.parseInt(enterQuarter));
        }
        if(!StringUtils.isEmpty(state)){
            if("0".equals(state)){
                sql.append(" and se.state = ? ");
            }
            if("1".equals(state)){
                sql.append(" and (se.state = ? or se.state is null) ");
            }
            param.add(Integer.parseInt(state));
        }
        sql.append(" group by sp.code,sp.name,s.code,s.name");
        if(null != sortMap) {
            sql.append(" order by ");
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
            }
        }
        studentExpensePageInfo = super.pageSqlQueryByNativeSql(studentExpensePageInfo, sql.toString(), field, param.toArray());
        return studentExpensePageInfo;
    }
}
