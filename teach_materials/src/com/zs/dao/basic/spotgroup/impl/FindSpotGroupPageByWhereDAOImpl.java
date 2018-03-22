package com.zs.dao.basic.spotgroup.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.SpotGroup;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/1.
 */
@Service("findSpotGroupPageByWhereDAO")
public class FindSpotGroupPageByWhereDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<SpotGroup> spotGroupPageInfo = new PageInfo<SpotGroup>();
        spotGroupPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        spotGroupPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM SpotGroup where 1=1 ");
        String spotCode = paramsMap.get("spotCode");
        String name = paramsMap.get("name");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and spotCode = ? ");
            param.add(spotCode);
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
        spotGroupPageInfo = super.pagedQueryByJpql(spotGroupPageInfo, sql.toString(), param.toArray());
        return spotGroupPageInfo;
    }
}
