package com.zs.dao.basic.user.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 实现分页查询用户信息接口
 * Created by Allen on 2015/4/27.
 */
@Service("findUserPageByWhereDAO")
public class FindUserPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<User> userPageInfo = new PageInfo<User>();
        userPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        userPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM User where 1=1 ");
        String loginName = paramsMap.get("loginName");
        String name = paramsMap.get("name");
        String type = paramsMap.get("type");
        String state = paramsMap.get("state");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(loginName)){
            sql.append(" and loginName = ? ");
            param.add(loginName);
        }
        if(!StringUtils.isEmpty(name)){
            sql.append(" and name like ? ");
            param.add("%"+name+"%");
        }
        if(!StringUtils.isEmpty(type)){
            sql.append(" and type = ? ");
            param.add(Integer.parseInt(type));
        }
        if(!StringUtils.isEmpty(state)){
            sql.append(" and state = ? ");
            param.add(Integer.parseInt(state));
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
