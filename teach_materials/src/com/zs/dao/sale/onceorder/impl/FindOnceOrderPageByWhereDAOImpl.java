package com.zs.dao.sale.onceorder.impl;

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
 * Created by Allen on 2015/5/26.
 */
@Service("findOnceOrderPageByWhereDAO")
public class FindOnceOrderPageByWhereDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo studentOrderPageInfo = new PageInfo();
        studentOrderPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        studentOrderPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        List<Object> param = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String spotCode = paramsMap.get("spotCode");
        String specCode = paramsMap.get("specCode");
        String levelCode = paramsMap.get("levelCode");
        String studentCode = paramsMap.get("studentCode");
        String studentName = paramsMap.get("studentName");
        String orderCode = paramsMap.get("orderCode");
        String state = paramsMap.get("state");
        String enterYear = paramsMap.get("enterYear");
        String quarter = paramsMap.get("quarter");
        String tmCount = paramsMap.get("tmCount");
        String studentSign = paramsMap.get("studentSign");
        String packageId = paramsMap.get("packageId");
        String isSendStudent = paramsMap.get("isSendStudent");


        String field = "sbop.sort, t.*";
        StringBuilder sql = new StringBuilder("from (select sbo.id, sbo.order_code, stu.code, stu.name, stu.spec_code, stu.level_code, stu.mobile, ifnull(sum(sbotm.count),0) as tmCount, ifnull(sum(sbotm.count * sbotm.price),0) as totalPrice, sbo.operator, sbo.operate_time, sbo.state, sbo.package_id, sbo.print_sort, sbo.student_sign from student_book_once_order sbo, sync_student stu, student_book_once_order_tm sbotm, teach_material tm where (CASE WHEN sbo.state < 3 THEN tm.state = 0 WHEN sbo.state >= 5 THEN sbotm.is_send = 1 ELSE 1=1 END) and tm.id = sbotm.teach_material_id and sbo.student_code = stu.code and sbo.id = sbotm.order_id ");
        if(!StringUtils.isEmpty(semesterId)){
            sql.append(" and sbo.semester_id = ? ");
            param.add(Long.parseLong(semesterId));
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append(" and stu.spot_code = ? ");
            param.add(spotCode);
        }
        if(!StringUtils.isEmpty(specCode)){
            sql.append(" and stu.spec_code = ? ");
            param.add(specCode);
        }
        if(!StringUtils.isEmpty(levelCode)){
            sql.append(" and stu.level_code = ? ");
            param.add(levelCode);
        }
        if(!StringUtils.isEmpty(studentCode)){
            sql.append(" and stu.code = ? ");
            param.add(studentCode);
        }
        if(!StringUtils.isEmpty(studentName)){
            sql.append(" and stu.name like ? ");
            param.add("%"+studentName+"%");
        }
        if(!StringUtils.isEmpty(enterYear)){
            sql.append(" and stu.study_enter_year = ? ");
            param.add(Integer.parseInt(enterYear));
        }
        if(!StringUtils.isEmpty(quarter)){
            sql.append(" and study_quarter = ? ");
            param.add(Integer.parseInt(quarter));
        }
        if(!StringUtils.isEmpty(orderCode)){
            sql.append(" and sbo.order_code = ? ");
            param.add(orderCode);
        }
        if(!StringUtils.isEmpty(state)){
            sql.append(" and sbo.state = ? ");
            param.add(Integer.parseInt(state));
        }
        if(!StringUtils.isEmpty(studentSign)){
            sql.append(" and sbo.student_sign = ? ");
            param.add(Integer.parseInt(studentSign));
        }
        if(!StringUtils.isEmpty(packageId)){
            sql.append(" and sbo.package_id = ? ");
            param.add(Long.parseLong(packageId));
        }
        if(!StringUtils.isEmpty(isSendStudent)){
            sql.append(" and sbo.is_send_student = ? ");
            param.add(Integer.parseInt(isSendStudent));
        }

        sql.append("group by sbo.id order by sbo.operate_time desc) t ");
        sql.append("LEFT JOIN student_book_order_package sbop on t.package_id = sbop.id where 1=1 ");
        if("0".equals(tmCount)){
            sql.append(" and t.tmCount > 0 ");
        }
        if(null != sortMap) {
            sql.append(" order by ");
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                sql.append(key);
                sql.append(" ");
                sql.append(sortMap.get(key) ? "asc" : "desc");
            }
        }
        studentOrderPageInfo = super.pageSqlQueryByNativeSql(studentOrderPageInfo, sql.toString(), field, param.toArray());
        return studentOrderPageInfo;
    }
}
