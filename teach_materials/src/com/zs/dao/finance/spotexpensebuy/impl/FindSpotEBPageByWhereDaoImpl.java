package com.zs.dao.finance.spotexpensebuy.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.finance.SpotExpenseBuy;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 根据学习中心编号分页查询学习中心消费记录
 * Created by LihongZhang on 2015/5/16.
 */
@Service("findSpotEBPageByWhereDao")
public class FindSpotEBPageByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO{
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<SpotExpenseBuy> spotExpenseBuyPageInfo = new PageInfo<SpotExpenseBuy>();
        spotExpenseBuyPageInfo.setCurrentPage(spotExpenseBuyPageInfo.getCurrentPage());
        spotExpenseBuyPageInfo.setCountOfCurrentPage(spotExpenseBuyPageInfo.getCountOfCurrentPage());
        List<Object> param = new ArrayList<Object>();

        StringBuilder sql = new StringBuilder("FROM SpotExpenseBuy where 1=1");
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
            sql.append(sortMap.get(key) ? "asc" : "desc");
        }
        spotExpenseBuyPageInfo = super.pagedQueryByJpql(spotExpenseBuyPageInfo, sql.toString(),param.toArray());
        return spotExpenseBuyPageInfo;
    }
}
