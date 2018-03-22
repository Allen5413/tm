package com.zs.dao.finance.studentexpensepay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *实现了根据学生学号分页查询学生入账明细的接口
 * Created by LihongZhang on 2015/5/15.
 */
@Service("findStuEPPageByWhereDao")
public class FindStuEPPageByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        String field = "s.code, s.name, s.spec_code, s.level_code, sep.money, sep.pay_type, sep.create_time, sep.arrival_time, sep.remark";
        StringBuilder sql = new StringBuilder("from student_expense_pay sep, sync_student s where sep.student_code = s.code ");

        String spotCode = paramsMap.get("spotCode");
        String studentCode = paramsMap.get("studentCode");
        String name = paramsMap.get("name");
        String enterYear = paramsMap.get("enterYear");
        String enterQuarter = paramsMap.get("enterQuarter");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String payType = paramsMap.get("payType");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");

        List<Object> param = new ArrayList<Object>();
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
        if(!StringUtils.isEmpty(specCode)){
            sql.append(" and s.spec_code = ? ");
            param.add(specCode);
        }
        if(!StringUtils.isEmpty(levelCode)){
            sql.append(" and s.level_code = ? ");
            param.add(levelCode);
        }
        if(!StringUtils.isEmpty(payType)){
            sql.append(" and sep.pay_type = ? ");
            param.add(Integer.parseInt(payType));
        }
        if(!StringUtils.isEmpty(beginDate)){
            sql.append(" and sep.create_time >= ? ");
            param.add(beginDate+" 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)){
            sql.append(" and sep.create_time <= ? ");
            param.add(endDate+" 23:59:59");
        }

        sql.append("order by sep.arrival_time desc, s.spot_code, s.code, sep.create_time desc ");
        super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
