package com.zs.dao.basic.press.Impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.Press;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 实现分页查询出版社信息的接口
 * Created by LihongZhang on 2015/5/3.
 */
@Service("findPressPageByWhereDao")
public class FindPressPageByWhereDaoImp extends BaseQueryDao implements FindPageByWhereDAO{

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<Press> pressPageInfo = new PageInfo<Press>();
        pressPageInfo.setCurrentPage( pageInfo.getCurrentPage());
        pressPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM Press where 1=1 ");
        String name = paramsMap.get("name");
        String code = paramsMap.get("code");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(name)){
            sql.append(" and name like ? ");
            param.add("%"+name+"%");
        }
        if(!StringUtils.isEmpty(code)){
            sql.append(" and code = ? ");
            param.add(code);
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
        pressPageInfo = super.pagedQueryByJpql(pressPageInfo, sql.toString(), param.toArray());
        return pressPageInfo;
    }
}
