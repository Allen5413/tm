package com.zs.dao.sync.eduwestuser.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.sync.EduwestUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/22.
 */
@Service("findEduwestUserPageByWhereDAO")
public class FindEduwestUserPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<EduwestUser> userPageInfo = new PageInfo<EduwestUser>();
        userPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        userPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM EduwestUser where 1=1 ");
        String pin = paramsMap.get("pin");
        String name = paramsMap.get("name");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(pin)){
            sql.append(" and pin = ? ");
            param.add(pin);
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
        userPageInfo = super.pagedQueryByJpql(userPageInfo, sql.toString(), param.toArray());
        return userPageInfo;
    }
}
