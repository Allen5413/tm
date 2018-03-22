package com.zs.dao.sale.onceordertm.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 打印学生发书单
 * Created by Allen on 2015/7/19.
 */
@Service("findOnceOrderTMForSpotPrintDAO")
public class FindOnceOrderTMForSpotPrintDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT s.spec_code, s.level_code, s.code, s.name, sbo.order_code, sbo.state, sbotm.course_code, tm.name as tmName, tm.author, sbotm.price, sbotm.count ");
        sql.append("FROM sync_student s, student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm ");
        sql.append("WHERE s.code = sbo.student_code AND sbo.id = sbotm.order_id AND sbotm.teach_material_id = tm.id AND tm.state = 0 AND sbotm.count > 0 AND sbo.is_send_student = 0 ");

        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String state = paramsMap.get("state");
        String operateTime = paramsMap.get("operateTime");

        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("AND sbo.semester_id = ? ");
            params.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(state)){
            sql.append("AND sbo.state = ? ");
            params.add(Integer.parseInt(state));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("AND s.spot_code = ? ");
            params.add(spotCode);
        }
        if(state.equals(StudentBookOnceOrder.STATE_SORTING+"")){
            sql.append("AND sbo.operate_time = ? ");
            params.add(operateTime);
        }
        sql.append("ORDER BY s.code, sbotm.order_id, sbotm.teach_material_id");
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), params.toArray());
        return list;
    }
}
