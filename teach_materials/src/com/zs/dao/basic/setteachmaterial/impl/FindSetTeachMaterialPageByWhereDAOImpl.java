package com.zs.dao.basic.setteachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.basic.SetTeachMaterial;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/4/30.
 */
@Service("findSetTeachMaterialPageByWhereDAO")
public class FindSetTeachMaterialPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo setTeachMaterialPageInfo = new PageInfo();
        setTeachMaterialPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        setTeachMaterialPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        String field = "stm.id, stm.name, stm.buy_course_code, c.name as courseName, stm.remark, stm.operator, stm.operate_time";
        StringBuilder sql = new StringBuilder("from set_teach_material stm left join sync_course c on stm.buy_course_code = c.code where 1=1 ");
        String name = paramsMap.get("name");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(name)){
            sql.append(" and stm.name like ? ");
            param.add("%"+name+"%");
        }
        sql.append(" order by ");
        for (Iterator it = sortMap.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            sql.append(key);
            sql.append(" ");
            sql.append(sortMap.get(key) ? "asc" : "desc");
        }
        setTeachMaterialPageInfo = super.pageSqlQueryByNativeSql(setTeachMaterialPageInfo, sql.toString(), field, param.toArray());
        return setTeachMaterialPageInfo;
    }
}
