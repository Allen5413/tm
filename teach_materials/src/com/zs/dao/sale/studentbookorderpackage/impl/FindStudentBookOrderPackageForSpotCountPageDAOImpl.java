package com.zs.dao.sale.studentbookorderpackage.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/27.
 */
@Service("findStudentBookOrderPackageForSpotCountPageDAO")
public class FindStudentBookOrderPackageForSpotCountPageDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {

    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> params = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String isSend = paramsMap.get("isSend");

        String field = "sp.code,sp.name,count(sbop.id) as packageCount";
        StringBuilder sbSql = new StringBuilder("FROM student_book_order_package sbop, sync_spot sp ");
        sbSql.append("WHERE sbop.spot_code = sp.code ");

        if(!StringUtils.isEmpty(semesterId)){
            sbSql.append("and sbop.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sbSql.append("and sbop.spot_code = ? ");
            params.add(spotCode);
        }
        if(!StringUtils.isEmpty(isSend)){
            //查询还没有邮寄的
            if("0".equals(isSend)){
                sbSql.append("and sbop.logistic_code IS NULL ");
            }
            //查询已经邮寄的
            if("1".equals(isSend)){
                sbSql.append("and sbop.logistic_code IS NOT NULL ");
            }
        }
        sbSql.append("GROUP BY sp. CODE, sp. NAME HAVING(count(sbop.id) > 0) ");
        if(null != sortMap) {
            sbSql.append("order by ");
            int i = 0;
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                if(0 < i){
                    sbSql.append(",");
                }
                String key = it.next().toString();
                sbSql.append(key);
                sbSql.append(" ");
                sbSql.append(sortMap.get(key) ? "asc" : "desc");
                i++;
            }
        }
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sbSql.toString(), field, params.toArray());
        return pageInfo;
    }
}
