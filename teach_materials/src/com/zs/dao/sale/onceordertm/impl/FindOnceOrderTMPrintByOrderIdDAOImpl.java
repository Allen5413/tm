package com.zs.dao.sale.onceordertm.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 打印一次性订单学生订单明细
 * Created by Allen on 2015/9/9.
 */
@Service("findOnceOrderTMPrintByOrderIdDAO")
public class FindOnceOrderTMPrintByOrderIdDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT sp.name AS specName, l.name AS levelName, s.code, s.name, sbo.order_code, sbo.state, c.code AS courseCode, c.name AS courseName, tm.name as tmName, tm.author, sbotm.price, sbotm.count, ss.year, ss.quarter, spot.code AS spotCode, spot.name AS spotName, sbo.print_sort ");
        sql.append("FROM semester ss, sync_spot spot, sync_student s, sync_spec sp, sync_level l, student_book_once_order sbo, student_book_once_order_tm sbotm, teach_material tm, sync_course c ");
        sql.append("WHERE ss.id = sbo.semester_id AND s.spot_code = spot.code AND s.code = sbo.student_code AND s.spec_code = sp.code AND s.level_code = l.code AND c.code = sbotm.course_code AND sbo.id = sbotm.order_id AND sbotm.teach_material_id = tm.id AND sbotm.is_send = 1 AND sbotm.count > 0 ");


        String ids = paramsMap.get("ids");
        String state = paramsMap.get("state");

        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isEmpty(ids)){
            sql.append("AND sbo.id in (");
            String[] idsArray = ids.split(",");
            for(int i=0; i<idsArray.length; i++){
                if(i == idsArray.length-1){
                    sql.append("?");
                }else{
                    sql.append("?,");
                }
                params.add(Long.parseLong(idsArray[i]));
            }
            sql.append(") ");
        }
        if(!StringUtils.isEmpty(state)){
            sql.append("AND sbo.state >= ? ");
            params.add(Integer.parseInt(state));
        }

        sql.append("ORDER BY s.code , sbotm.teach_material_id");
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), params.toArray());
        return list;
    }
}
