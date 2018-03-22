package com.zs.dao.basic.courseteachmaterial.impl;

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
 * Created by Allen on 2015/12/12 0012.
 */
@Service("findCourseTeachMaterialPageByWhereDAO")
public class FindPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        long semesterId = Long.parseLong(paramsMap.get("semesterId"));
        String name = paramsMap.get("name");
        String code = paramsMap.get("code");
        String isNotTM = paramsMap.get("isNotTM");

        List<Object> param = new ArrayList<Object>();

        String field = "t.*";
        StringBuilder sql = new StringBuilder("from (");
        sql.append("select DISTINCT ctm.course_code, c.name courseName, tm.name tmName, tm.author, tm.price, p.name, ctm.operator, ctm.operate_time ");
        sql.append("from course_teach_material ctm LEFT JOIN sync_course c ON ctm.course_code = c.code ");
        sql.append("LEFT JOIN teach_material tm ON ctm.teach_material_id = tm.id ");
        sql.append("LEFT JOIN press p ON tm.press_id = p.id ");
        sql.append("WHERE ctm.semester_id = ? ");
        param.add(semesterId);

        if(!StringUtils.isEmpty(name)){
            sql.append("and tm.name like ? ");
            param.add("%"+name+"%");
        }
        if(!StringUtils.isEmpty(isNotTM) && "0".equals(isNotTM)){
            sql.append("and tm.name is null ");
        }
        if(!StringUtils.isEmpty(isNotTM) && "1".equals(isNotTM)){
            sql.append("and tm.name is not null ");
        }
        if(!StringUtils.isEmpty(code)){
            sql.append("and ctm.course_code = ? ");
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
        sql.append(") t");
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
