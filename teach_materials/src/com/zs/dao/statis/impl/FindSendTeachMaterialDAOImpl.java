package com.zs.dao.statis.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 统计发书数量
 * Created by Allen on 2015/12/1.
 */
@Service("findSendTeachMaterialDAO")
public class FindSendTeachMaterialDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String name = paramsMap.get("name");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");
        String tmTypeId = paramsMap.get("tmTypeId");

        StringBuilder sql = new StringBuilder();
        sql.append("select name, pname, author, price, sum(count) from (");
        sql.append("select tm.name, p.name pname, tm.author, sbotm.price, sum(sbotm.count) count ");
        sql.append("from student_book_order sbo, student_book_order_tm sbotm, teach_material tm, press p, student_book_order_package sbop ");
        sql.append("where sbo.order_code = sbotm.order_code and sbotm.teach_material_id = tm.id and tm.press_id = p.id and sbo.package_id = sbop.id ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and sbo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(name)){
            sql.append("and tm.name like ? ");
            param.add("%"+name+"%");
        }
        if(!StringUtils.isEmpty(tmTypeId)){
            sql.append("and tm.teach_material_type_id = ? ");
            param.add(Integer.parseInt(tmTypeId));
        }
        sql.append("and sbo.state > 3 and sbotm.count > 0 and sbotm.is_send = 1 ");
        if(!StringUtils.isEmpty(beginDate)){
            sql.append("and sbop.send_time >= ? ");
            param.add(beginDate + " 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)){
            sql.append("and sbop.send_time <= ? ");
            param.add(endDate+" 00:00:00");
        }
        sql.append("group by tm.`name`, p.`name`, tm.author, sbotm.price ");

        sql.append("union ALL ");

        sql.append("select tm.`name`, p.`name` pname, tm.author, potm.tm_price price, sum(potm.count) count ");
        sql.append("from teach_material_place_order tmpo, place_order_teach_material potm, teach_material tm, press p, place_order_package pop ");
        sql.append("where tmpo.id = potm.order_id and potm.teach_material_id = tm.id and tm.press_id = p.id and tmpo.package_id = pop.id ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(name)){
            sql.append("and tm.name like ? ");
            param.add("%"+name+"%");
        }
        sql.append("and tmpo.order_status > '3' and potm.count > 0 and potm.is_send = 1 ");
        if(!StringUtils.isEmpty(beginDate)){
            sql.append("and pop.send_time >= ? ");
            param.add(beginDate + " 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)){
            sql.append("and pop.send_time <= ? ");
            param.add(endDate+" 00:00:00");
        }
        sql.append("group by tm.`name`, p.`name`, tm.author, price ");

        sql.append("union ALL ");

        sql.append("select tm.name, p.name pname, tm.author, sbotm.price, sum(sbotm.count) count ");
        sql.append("from student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm, press p, student_book_order_package sbop ");
        sql.append("where sbo.id = sbotm.order_id and sbotm.teach_material_id = tm.id and tm.press_id = p.id and sbo.package_id = sbop.id ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and sbo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(name)){
            sql.append("and tm.name like ? ");
            param.add("%"+name+"%");
        }
        if(!StringUtils.isEmpty(tmTypeId)){
            sql.append("and tm.teach_material_type_id = ? ");
            param.add(Integer.parseInt(tmTypeId));
        }
        sql.append("and sbo.state > 4 and sbotm.count > 0 and sbotm.is_send = 1 ");
        if(!StringUtils.isEmpty(beginDate)){
            sql.append("and sbop.send_time >= ? ");
            param.add(beginDate + " 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)){
            sql.append("and sbop.send_time <= ? ");
            param.add(endDate+" 00:00:00");
        }
        sql.append("group by tm.`name`, p.`name`, tm.author, sbotm.price ");
        sql.append(") t group by name, name, author, price");


        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
