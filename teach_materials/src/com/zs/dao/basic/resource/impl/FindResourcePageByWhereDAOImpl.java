package com.zs.dao.basic.resource.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 实现分页查询资源信息接口
 * Created by Allen on 2015/4/28.
 */
@Service("findResourcePageByWhereDAO")
public class FindResourcePageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<Resource> resourcePageInfo = new PageInfo<Resource>();
        resourcePageInfo.setCurrentPage(pageInfo.getCurrentPage());
        resourcePageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM Resource where 1=1 ");
        String name = paramsMap.get("name");
        String menuId = paramsMap.get("menuId");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(name)){
            sql.append(" and name like ? ");
            param.add("%"+name+"%");
        }
        if(!StringUtils.isEmpty(menuId)){
            sql.append(" and menuId = ? ");
            param.add(Long.parseLong(menuId));
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
        resourcePageInfo = super.pagedQueryByJpql(resourcePageInfo, sql.toString(), param.toArray());
        return resourcePageInfo;
    }


}
