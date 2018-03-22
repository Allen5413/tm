package com.zs.dao.finance.spotexpenseoth.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 直接从学生缴费表统计数据，不从spot_expense_oth查询
 * Created by Allen on 2016/3/25.
 */
@Service("findSpotExpenseOthForCountDAO")
public class FindSpotExpenseOthForCountDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        String field = "t.*";
//        StringBuilder sql = new StringBuilder("from (select s.id, s.year, s.quarter, prov.name pName, sp.code, sp.name, sum(ifnull(se.pay, 0)) totalPay, " +
//                "sum(ifnull(se.buy, 0)) totalBuy, sum(case when ifnull(se.pay, 0) - ifnull(se.buy, 0) > 0 then ifnull(se.pay, 0) - ifnull(se.buy, 0) else 0 end) acc, " +
//                "sum(case when ifnull(se.pay, 0) - ifnull(se.buy, 0) > 0 then 0 else ifnull(se.buy, 0) - ifnull(se.pay, 0) end) own, " +
//                "seo.clear_time, seo.id seoId ");
//        sql.append("FROM " +
//                "semester s INNER JOIN student_expense se on s.id = se.semester_id " +
//                "INNER JOIN sync_student stu on se.student_code = stu.code " +
//                "INNER JOIN sync_spot sp on stu.spot_code = sp.code " +
//                "LEFT JOIN sync_spot_province sprov on sp.code = sprov.spot_code " +
//                "LEFT JOIN sync_province prov on sprov.province_code = prov.code " +
//                "INNER JOIN spot_expense_oth seo ON s.id = seo.semester_id and sp.code = seo.spot_code WHERE 1=1 ");

        StringBuilder sql = new StringBuilder("from (select s.id, s.year, s.quarter, '-' pName, sp.code, sp.name, sum(ifnull(se.pay, 0)) totalPay, " +
                "sum(ifnull(se.buy, 0)) totalBuy, sum(case when ifnull(se.pay, 0) - ifnull(se.buy, 0) > 0 then ifnull(se.pay, 0) - ifnull(se.buy, 0) else 0 end) acc, " +
                "sum(case when ifnull(se.pay, 0) - ifnull(se.buy, 0) > 0 then 0 else ifnull(se.buy, 0) - ifnull(se.pay, 0) end) own, " +
                "seo.clear_time, seo.id seoId ");
        sql.append("FROM " +
                "semester s INNER JOIN student_expense se on s.id = se.semester_id " +
                "INNER JOIN sync_student stu on se.student_code = stu.code " +
                "INNER JOIN sync_spot sp on stu.spot_code = sp.code " +
                "INNER JOIN spot_expense_oth seo ON s.id = seo.semester_id and sp.code = seo.spot_code WHERE 1=1 ");

        String provinceId = paramsMap.get("provinceId");
        String spotId = paramsMap.get("spotId");
        String semesterId = paramsMap.get("semesterId");

        List<Object> param = new ArrayList<Object>();
//        if(!StringUtils.isEmpty(provinceId)){
//            sql.append("and prov.code = ? ");
//            param.add(provinceId);
//        }
        if(!StringUtils.isEmpty(spotId)){
            sql.append("and sp.code = ? ");
            param.add(spotId);
        }
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and s.id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        sql.append("GROUP BY s.id, sp.code) t ORDER BY t.id, t.code");
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
