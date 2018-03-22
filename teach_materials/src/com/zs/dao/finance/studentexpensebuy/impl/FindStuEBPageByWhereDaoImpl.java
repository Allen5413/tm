package com.zs.dao.finance.studentexpensebuy.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.finance.StudentExpenseBuy;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *实现了根据学生学号分页查询学生消费明细的接口
 * Created by LihongZhang on 2015/5/15.
 */
@Service("findStuEBPageByWhereDao")
public class FindStuEBPageByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<StudentExpenseBuy> semesterPageInfo = new PageInfo<StudentExpenseBuy>();
        semesterPageInfo.setCurrentPage( semesterPageInfo.getCurrentPage());
        semesterPageInfo.setCountOfCurrentPage(semesterPageInfo.getCountOfCurrentPage());
        List<Object> param = new ArrayList<Object>();

        StringBuilder sql = new StringBuilder("FROM StudentExpenseBuy where 1=1");
        String studentCode = paramsMap.get("studentCode");
        if (!StringUtils.isEmpty(studentCode)){
            sql.append(" and studentCode = ?");
            param.add(studentCode);
        }

        sql.append(" order by ");
        for (Iterator it = sortMap.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            sql.append(key);
            sql.append(" ");
            sql.append(sortMap.get(key) ? "asc" : "desc");
        }
        semesterPageInfo = super.pagedQueryByJpql(semesterPageInfo, sql.toString(),param.toArray());
        return semesterPageInfo;
    }
}
