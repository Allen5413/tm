package com.zs.dao.finance.spotexpense.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.finance.SpotExpense;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 分页查询学习中心费用
 * 查询条件有学习中心编号（精确查询）
 * Created by LihongZhang on 2015/5/16.
 */
@Service("findSpotEPageByWhereDao")
public class FindSpotEPageByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO{
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<SpotExpense> spotExpensePageInfo = new PageInfo<SpotExpense>();
        spotExpensePageInfo.setCurrentPage(pageInfo.getCurrentPage());
        spotExpensePageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        String field = "se.spot_code, s.name, se.pay, se.buy, se.confirmed_money, se.operator, se.operate_time, ss.year, ss.quarter, se.discount, se.id ";
        StringBuilder sql = new StringBuilder("from spot_expense se LEFT JOIN sync_spot s ON se.spot_code = s.code " +
                "LEFT JOIN sync_spot_province sp ON sp.spot_code = s.code " +
                "LEFT JOIN semester ss ON se.semester_id = ss.id where 1 = 1 ");

        String provCode = paramsMap.get("provCode");
        String spotCode = paramsMap.get("spotCode");
        String semesterId = paramsMap.get("semesterId");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(semesterId)){
            sql.append(" and se.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(provCode)){
            sql.append(" and sp.province_code = ? ");
            param.add(provCode);
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and se.spot_code = ? ");
            param.add(spotCode);
        }
        sql.append(" order by se.spot_code, se.semester_id ");
        spotExpensePageInfo = super.pageSqlQueryByNativeSql(spotExpensePageInfo, sql.toString(), field, param.toArray());
        return spotExpensePageInfo;
    }
}
