package com.zs.dao.sync.province.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.sync.Province;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Service("findProvincePageByWhereDAO")
public class FindProvincePageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<Province> provincePageInfo = new PageInfo<Province>();
        provincePageInfo.setCurrentPage(pageInfo.getCurrentPage());
        provincePageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM Province where 1=1 ");
        String code = paramsMap.get("code");
        String name = paramsMap.get("name");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(code)){
            sql.append(" and code = ? ");
            param.add(code);
        }
        if(!StringUtils.isEmpty(name)){
            sql.append(" and name like ? ");
            param.add("%"+name+"%");
        }
        sql.append(" order by ");
        for (Iterator it = sortMap.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            sql.append(key);
            sql.append(" ");
            sql.append(sortMap.get(key) ? "asc" : "desc");
        }
        provincePageInfo = super.pagedQueryByJpql(provincePageInfo, sql.toString(), param.toArray());
        return provincePageInfo;
    }
}
