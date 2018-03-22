package com.zs.dao.basic.spotgroupstudent.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.SpotGroupStudent;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/1.
 */
@Service("findSpotGroupStudentPageByWhereDAO")
public class FindSpotGroupStudentPageByWhereDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<SpotGroupStudent> spotGroupStudentPageInfo = new PageInfo<SpotGroupStudent>();
        spotGroupStudentPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        spotGroupStudentPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM SpotGroupStudent where 1=1 ");
        String spotGroupId = paramsMap.get("spotGroupId");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(spotGroupId)){
            sql.append(" and spotGroupId = ? ");
            param.add(Long.parseLong(spotGroupId));
        }
        sql.append(" order by ");
        for (Iterator it = sortMap.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            sql.append(key);
            sql.append(" ");
            sql.append(sortMap.get(key) ? "asc" : "desc");
        }
        spotGroupStudentPageInfo = super.pagedQueryByJpql(spotGroupStudentPageInfo, sql.toString(), param.toArray());
        return spotGroupStudentPageInfo;
    }
}
