package com.zs.dao.placeorder.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/12/19.
 */
@Service("countSpecAllOrderDAO")
public class CountSpecAllOrderDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");

        StringBuilder sql = new StringBuilder();
        sql.append("select t.code, t.name, sum(t.totalPrice) totalPrice from ");
        sql.append("(" +
                "SELECT s.code, s.name, sum(potm.count*tm.price) totalPrice " +
                "FROM sync_spot s, teach_material_place_order tmpo, place_order_teach_material potm, " +
                "teach_material_course tmc, teach_material tm " +
                "WHERE s.code = tmpo.spot_code AND tmpo.id = potm.order_id AND tmpo.is_spec_all = 1 " +
                "AND potm.course_code = tmc.course_code AND tmc.teach_material_id = tm.id AND tm.state = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and tmpo.spot_code = ? ");
            param.add(spotCode);
        }
        sql.append("GROUP BY s.code ");
        sql.append("union all ");

        sql.append("SELECT s.code, s.name, sum(potm.count*tm.price) totalPrice " +
                "FROM sync_spot s, teach_material_place_order tmpo, place_order_teach_material potm, " +
                "set_teach_material stm, set_teach_material_tm stmtm, teach_material tm " +
                "WHERE s.code = tmpo.spot_code AND tmpo.id = potm.order_id AND tmpo.is_spec_all = 1 " +
                "AND potm.course_code = stm.buy_course_code AND stmtm.set_teach_material_id = stm.id " +
                "AND stmtm.teach_material_id = tm.id AND tm.state = 0 ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and tmpo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and tmpo.spot_code = ? ");
            param.add(spotCode);
        }
        sql.append("GROUP BY s.code ");
        sql.append(") t group by t.code, t.name order by t.code");

        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
