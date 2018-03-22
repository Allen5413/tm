package com.zs.dao.finance.spotexpensepay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.finance.SpotExpensePay;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 根据学习中心编号分页查询学习中心入账记录
 * Created by LihongZhang on 2015/5/16.
 */
@Service("findSpotEPPageByWhereDao")
public class FindSpotEPPageByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO{
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<SpotExpensePay> spotExpensePayPageInfo = new PageInfo<SpotExpensePay>();
        spotExpensePayPageInfo.setCurrentPage(spotExpensePayPageInfo.getCurrentPage());
        spotExpensePayPageInfo.setCountOfCurrentPage(spotExpensePayPageInfo.getCountOfCurrentPage());
        List<Object> param = new ArrayList<Object>();

        StringBuilder sql = new StringBuilder("FROM SpotExpensePay where 1=1");
        String spotCode = paramsMap.get("spotCode");
        if (!StringUtils.isEmpty(spotCode)){
            sql.append(" and spotCode = ?");
            param.add(spotCode);
        }
        sql.append(" order by ");
        for (Iterator it = sortMap.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            sql.append(key);
            sql.append(" ");
            sql.append(sortMap.get(key)?"ase":"desc");
        }
        spotExpensePayPageInfo = super.pagedQueryByJpql(spotExpensePayPageInfo, sql.toString(),param.toArray());
        return spotExpensePayPageInfo;
    }
}
