package com.zs.dao.sale.studentbookorderpackage.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.sale.StudentBookOrderPackage;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/7/22.
 */
@Service("findStudentBookOrderPackagePageByWhereDAO")
public class FindStudentBookOrderPackagePageByWhereDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo studentOrderPageInfo = new PageInfo();
        studentOrderPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        studentOrderPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String code = paramsMap.get("code");
        String logisticCode = paramsMap.get("logisticCode");
        String isSign = paramsMap.get("isSign");
        String beginSendDate = paramsMap.get("beginSendDate");
        String endSendDate = paramsMap.get("endSendDate");
        String isSend = paramsMap.get("isSend");
        String isOnce = paramsMap.get("isOnce");


        String field = "t.*";
        StringBuilder sql = new StringBuilder("FROM (select sp.name sName, kp.data, sbop.*, count(sbo.order_code) as orderCount ");
        sql.append("FROM student_book_order_package sbop LEFT JOIN kuaidi_push kp on sbop.logistic_code = kp.nu ");
        sql.append("INNER JOIN sync_spot sp on sbop.spot_code = sp.code ");
        if(!StringUtils.isEmpty(isOnce) && Integer.parseInt(isOnce) == StudentBookOrderPackage.IS_ONCE_YES){
            sql.append("INNER JOIN student_book_once_order sbo on sbop.id = sbo.package_id ");
        }else{
            sql.append("INNER JOIN student_book_order sbo on sbop.id = sbo.package_id ");
        }
        sql.append("WHERE 1=1 ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("AND sbop.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("AND sbop.spot_code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(code)){
            sql.append("AND sbop.code = ? ");
            param.add(code);
        }
        if(!StringUtils.isEmpty(logisticCode)){
            sql.append("AND sbop.logistic_code = ? ");
            param.add(logisticCode);
        }
        if(!StringUtils.isEmpty(isSend)){
            //查询还没有邮寄的
            if("0".equals(isSend)){
                sql.append("AND sbop.logistic_code IS NULL ");
            }
            //查询已经邮寄的
            if("1".equals(isSend)){
                sql.append("AND sbop.logistic_code IS NOT NULL ");
            }
        }
        if(!StringUtils.isEmpty(isSign)){
            sql.append("AND sbop.is_sign = ? ");
            param.add(Integer.parseInt(isSign));
        }
        if(!StringUtils.isEmpty(isOnce)){
            sql.append("AND sbop.is_once = ? ");
            param.add(Integer.parseInt(isOnce));
        }
        if(!StringUtils.isEmpty(beginSendDate)){
            sql.append("AND sbop.send_time >= ? ");
            param.add(beginSendDate+" 00:00:00");
        }
        if(!StringUtils.isEmpty(endSendDate)){
            sql.append("AND sbop.send_time <= ? ");
            param.add(endSendDate+" 23:59:59");
        }
        sql.append("group by sName, data, id, semester_id, code, spot_code, sort, logistic_code, is_sign, send_time, creator, create_time, operator, operate_time, version having(count(sbo.order_code) > 0) ");
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
        studentOrderPageInfo = super.pageSqlQueryByNativeSql(studentOrderPageInfo, sql.toString(), field, param.toArray());
        return studentOrderPageInfo;
    }
}

