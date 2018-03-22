package com.zs.dao.basic.semester.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.Semester;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 学期的分页查询
 * 无查询条件，按照学年，季度倒排序
 * Created by LihongZhang on 2015/5/11.
 */
@Service("findSemesterPageByWhereDao")
public class FindSemesterPageByWhereDaoImpl extends BaseQueryDao implements FindPageByWhereDAO{
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo<Semester> semesterPageInfo = new PageInfo<Semester>();
        semesterPageInfo.setCurrentPage( pageInfo.getCurrentPage());
        semesterPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        StringBuilder sql = new StringBuilder("FROM Semester where 1=1 ");
        sql.append("order by year desc,quarter desc");
        semesterPageInfo = super.pagedQueryByJpql(semesterPageInfo, sql.toString(), null);
        return semesterPageInfo;
    }
}
