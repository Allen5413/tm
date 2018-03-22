package com.zs.dao.basic.environment.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.Environment;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by LihongZhang on 2015/5/27.
 */
@Service("findEnvironmentByWhereDao")
public class FindEnvironmentByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO{
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<Environment> menuPageInfo = new PageInfo<Environment>();
        menuPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        menuPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM Environment where 1=1 ");
        String name = paramsMap.get("name");
        String code = paramsMap.get("code");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(name)){
            sql.append(" and name = ? ");
            param.add(name);
        }
        if(!StringUtils.isEmpty(code)){
            sql.append(" and code = ? ");
            param.add(code);
        }
        sql.append(" order by ");
        for (Iterator it = sortMap.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            sql.append(key);
            sql.append(" ");
            sql.append(sortMap.get(key) ? "asc" : "desc");
        }
        menuPageInfo = super.pagedQueryByJpql(menuPageInfo, sql.toString(), param.toArray());
        return menuPageInfo;
    }
}
