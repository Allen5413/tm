package com.zs.dao.basic.menu.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.Menu;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 实现分页查询菜单信息接口
 * Created by Allen on 2015/4/28.
 */
@Service("findMenuPageByWhereDAO")
public class FindMenuPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<Menu> menuPageInfo = new PageInfo<Menu>();
        menuPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        menuPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM Menu where 1=1 ");
        String name = paramsMap.get("name");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(name)){
            sql.append(" and name like ? ");
            param.add("%"+name+"%");
        }
        if(null != sortMap) {
            sql.append("order by ");
            int i = 0;
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                if(0 < i){
                    sql.append(",");
                }
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
                i++;
            }
        }
        menuPageInfo = super.pagedQueryByJpql(menuPageInfo, sql.toString(), param.toArray());
        return menuPageInfo;
    }
}
