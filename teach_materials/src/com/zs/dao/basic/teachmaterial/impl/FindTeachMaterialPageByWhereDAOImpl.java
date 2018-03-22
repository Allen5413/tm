package com.zs.dao.basic.teachmaterial.impl;

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
 * 实现分页查询教材信息接口
 * Created by Allen on 2015/4/29.
 */
@Service("findTeachMaterialPageByWhereDAO")
public class FindTeachMaterialPageByWhereDAOImpl extends BaseQueryDao implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo teachMaterialPageInfo = new PageInfo();
        teachMaterialPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        teachMaterialPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        String field = "DISTINCT tm.id, tm.isbn, tm.name, tm.author, tm.revision, tm.price, tm.state, tm.is_set, tm.operator, tm.operate_time, tm.is_spot_send, p.name as pressName, tmt.name as tmTypeName ";
        StringBuilder sql = new StringBuilder("from teach_material tm left join press p on tm.press_id = p.id " +
                "left join teach_material_type tmt on tm.teach_material_type_id = tmt.id " +
                "left join teach_material_course tmc on tm.id = tmc.teach_material_id " +
                "LEFT JOIN set_teach_material_tm stmtm ON tm.id = stmtm.teach_material_id " +
                "LEFT JOIN set_teach_material stm ON stmtm.set_teach_material_id = stm.id " +
                "where 1=1 ");
        String isbn = paramsMap.get("isbn");
        String name = paramsMap.get("name");
        String isExactName = paramsMap.get("isExactName");
        String typeId = paramsMap.get("typeId");
        String pressId = paramsMap.get("pressId");
        String state = paramsMap.get("state");
        String courseCode = paramsMap.get("courseCode");
        String author = paramsMap.get("author");
        String price = paramsMap.get("price");

        List<Object> param = new ArrayList<Object>();
        if(!StringUtils.isEmpty(isbn)){
            sql.append(" and tm.isbn = ? ");
            param.add(isbn);
        }
        if(!StringUtils.isEmpty(name)){
            if(StringUtils.isEmpty(isExactName)) {
                sql.append(" and tm.name like ? ");
                param.add("%"+name+"%");
            }else{
                sql.append(" and tm.name = ? ");
                param.add(name);
            }
        }
        if(!StringUtils.isEmpty(typeId)){
            sql.append(" and tm.teach_material_type_id = ? ");
            param.add(Long.parseLong(typeId));
        }
        if(!StringUtils.isEmpty(pressId)){
            sql.append(" and tm.press_id = ? ");
            param.add(Long.parseLong(pressId));
        }
        if(!StringUtils.isEmpty(author)){
            sql.append(" and tm.author = ? ");
            param.add(author);
        }
        if(!StringUtils.isEmpty(price)){
            sql.append(" and tm.price = ? ");
            param.add(Float.parseFloat(price));
        }
        if(!StringUtils.isEmpty(state)){
            sql.append(" and tm.state = ? ");
            param.add(Integer.parseInt(state));
        }
        if(!StringUtils.isEmpty(courseCode)){
            sql.append(" and (? LIKE CONCAT('%', tmc.course_code, '%') OR ? LIKE CONCAT('%', stm.buy_course_code, '%') ) ");
            param.add(courseCode);
            param.add(courseCode);
            field += ", IFNULL(tmc.course_code,stm.buy_course_code) course_code ";
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
        teachMaterialPageInfo = super.pageSqlQueryByNativeSql(teachMaterialPageInfo, sql.toString(), field, param.toArray());
        return teachMaterialPageInfo;
    }
}
