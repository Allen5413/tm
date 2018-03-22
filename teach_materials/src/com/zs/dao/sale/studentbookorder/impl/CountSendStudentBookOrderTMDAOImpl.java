package com.zs.dao.sale.studentbookorder.impl;

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
 * 统计已发出的学生订单的教材
 * Created by Allen on 2015/9/4.
 */
@Service("countSendStudentBookOrderTMDAO")
public class CountSendStudentBookOrderTMDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String tmName = paramsMap.get("tmName");
        String spotCodes = paramsMap.get("spotCodes");
        String studentCode = paramsMap.get("studentCode");
        String beginDate = paramsMap.get("beginDate");
        String endDate = paramsMap.get("endDate");

        String field = "sbotm.teach_material_id, tm.name, tm.isbn, tm.author, sbotm.price, sum(sbotm.count) count";
        StringBuilder sql = new StringBuilder("from teach_material tm, student_book_order sbo,  student_book_order_tm sbotm, sync_student s, student_book_order_package sbop ");
        sql.append("where tm.id = sbotm.teach_material_id and sbo.order_code = sbotm.order_code and sbo.package_id = sbop.id and sbo.student_code = s.code and sbotm.is_send = 1 and sbo.state > 3 and sbotm.count > 0 ");

        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and sbo.semester_id = ? ");
            param.add(semesterId);
        }
        if(!StringUtils.isEmpty(tmName)){
            sql.append("and tm.name like ? ");
            param.add("%"+tmName+"%");
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
        sql.append("group by sbotm.teach_material_id, tm.`name`, tm.isbn, tm.author, sbotm.price");

        if(null != sortMap) {
            sql.append(" order by ");
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
            }
        }
        pageInfo = super.pageSqlQueryByNativeSql(pageInfo, sql.toString(), field, param.toArray());
        return pageInfo;
    }
}
