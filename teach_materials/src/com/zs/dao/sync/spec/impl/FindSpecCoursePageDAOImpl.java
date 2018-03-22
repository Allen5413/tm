package com.zs.dao.sync.spec.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2016/10/20.
 */
@Service("findSpecCoursePageDAO")
public class FindSpecCoursePageDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        String field = "t.*";
        StringBuilder sql = new StringBuilder("from (select spec_code, level_code from sync_spec_course GROUP BY spec_code, level_code ORDER BY spec_code) t ");
        super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, null);
        return pageInfo;
    }
}
