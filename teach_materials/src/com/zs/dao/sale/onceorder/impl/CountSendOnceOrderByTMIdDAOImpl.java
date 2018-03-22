package com.zs.dao.sale.onceorder.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/4.
 */
@Service("countSendOnceOrderByTMIdDAO")
public class CountSendOnceOrderByTMIdDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String tmId = paramsMap.get("tmId");
        String spotCodes = paramsMap.get("spotCodes");
        String studentCode = paramsMap.get("studentCode");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");
        String price = paramsMap.get("price");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT sbo.order_code, s.spot_code, sp.name spotName, sbo.student_code, s.name, sbotm.price, sbotm.count, sbotm.id ");
        sql.append("FROM student_book_once_order sbo, student_book_once_order_tm sbotm, sync_student s, sync_spot sp, student_book_order_package sbop ");
        sql.append("WHERE sbo.id = sbotm.order_id AND sbo.package_id = sbop.id AND sbo.student_code = s.code AND sp.code = s.spot_code AND sbo.state > 4 AND sbotm.count > 0 ");

        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and sbo.semester_id = ? ");
            param.add(semesterId);
        }
        if(!StringUtils.isEmpty(tmId)){
            sql.append("and sbotm.teach_material_id = ? ");
            param.add(Long.parseLong(tmId));
        }
        if(!StringUtils.isEmpty(price)){
            sql.append("and sbotm.price = ? ");
            param.add(Float.parseFloat(price));
        }
        if(!StringUtils.isEmpty(spotCodes)){
            String[] spotCodeArray = spotCodes.split(",");
            if(null != spotCodeArray && 0 < spotCodeArray.length){
                sql.append("and s.spot_code in (");
                int i = 0;
                for (String spotCode : spotCodeArray){
                    if(i == spotCodeArray.length - 1){
                        sql.append("?");
                    }else {
                        sql.append("?, ");
                    }
                    param.add(spotCode);
                    i++;
                }
                sql.append(") ");
            }
        }
        if(!StringUtils.isEmpty(studentCode)){
            sql.append("and sbo.student_code = ? ");
            param.add(studentCode);
        }
        if(!StringUtils.isEmpty(beginDate)){
            sql.append("and sbop.send_time >= ? ");
            param.add(beginDate+" 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)){
            sql.append("and sbop.send_time <= ? ");
            param.add(endDate+" 23:59:59");
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
        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
