package com.zs.dao.finance.invoice.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/4 0004.
 */
@Service("findInvoiceByWhereDAO")
public class FindInvoiceByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        String field = "i.id, i.spot_code, s.code, IFNULL(s.name, ss.name) name, s.spec_code, s.level_code, i.`code` iCode, i.money, i.state, i.open_time, i.operator, i.operate_time";
        StringBuilder sql = new StringBuilder("from invoice i LEFT JOIN sync_student s on i.student_code = s.code LEFT JOIN sync_spot ss on i.spot_code = ss.code ");
        sql.append("where 1=1 ");

        String studentCode = paramsMap.get("studentCode");
        String spotCode = paramsMap.get("spotCode");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String state = paramsMap.get("state");
        String year = paramsMap.get("year");
        String quarter = paramsMap.get("quarter");
        String isTotal = paramsMap.get("isTotal");
        String openDateBegin = paramsMap.get("openDateBegin");
        String openDateEnd = paramsMap.get("openDateEnd");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and i.spot_code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(studentCode)){
            sql.append("and s.code = ? ");
            param.add(studentCode);
        }
        if(!StringUtils.isEmpty(year)){
            sql.append("and s.study_enter_year = ? ");
            param.add(Integer.parseInt(year));
        }
        if(!StringUtils.isEmpty(year)){
            sql.append("and s.study_quarter = ? ");
            param.add(Integer.parseInt(quarter));
        }
        if(!StringUtils.isEmpty(year)){
            sql.append("and s.spec_code = ? ");
            param.add(specCode);
        }
        if(!StringUtils.isEmpty(year)){
            sql.append("and s.level_code = ? ");
            param.add(levelCode);
        }
        if(!StringUtils.isEmpty(state)){
            sql.append("and i.state = ? ");
            param.add(Integer.parseInt(state));
        }
        if(!StringUtils.isEmpty(isTotal)){
            if("0".equals(isTotal)){
                sql.append("and i.student_code is not null ");
            }
            if("1".equals(isTotal)){
                sql.append("and i.student_code is null ");
            }
        }
        if(!StringUtils.isEmpty(openDateBegin)){
            sql.append("and i.open_time >= ? ");
            param.add(openDateBegin+" 00:00:00");
        }
        if(!StringUtils.isEmpty(openDateEnd)){
            sql.append("and i.open_time <= ? ");
            param.add(openDateEnd+" 23:59:59");
        }
        sql.append("order by i.operate_time desc");
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
