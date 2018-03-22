package com.zs.dao.sale.onceorder.impl;

import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindListByWhereDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 统计一次性订单中心确认情况
 * Created by Allen on 2015/9/4.
 */
@Service("countOnceOrderForConfirmDAO")
public class CountOnceOrderForConfirmDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        List<Object> param = new ArrayList<Object>();
        String provCode = paramsMap.get("provCode");
        String spotCode = paramsMap.get("spotCode");

        StringBuilder sql = new StringBuilder();
        sql.append("select t.spot_code, t.name, t.totalCount, t.count, t2.totalPrice, t2.price from ");
        sql.append("(" +
                "select sp.spot_code, st.name, count(*) totalCount, sum(case when sbo.state > 0 then 1 else 0 end) count " +
                "from sync_spot_province sp, sync_student s, student_book_once_order sbo, sync_spot st " +
                "where sp.spot_code = s.spot_code and s.code = sbo.student_code and sp.spot_code = st.code ");
        if(!StringUtils.isEmpty(provCode)){
            sql.append("and sp.province_code = ? ");
            param.add(provCode);
        }
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and sp.spot_code = ? ");
            param.add(spotCode);
        }
        sql.append("group by sp.spot_code, st.name) t, ");
        sql.append("(" +
                "select s.spot_code, sum(sbotm.price) totalPrice, sum(case when sbo.state > 0 then sbotm.count*sbotm.price else 0 end) price " +
                "from sync_student s, student_book_once_order sbo, student_book_once_order_tm sbotm " +
                "where s.code = sbo.student_code and sbo.id = sbotm.order_id ");
        if(!StringUtils.isEmpty(spotCode)){
            sql.append("and s.spot_code = ? ");
            param.add(spotCode);
        }
        sql.append("group by s.spot_code) t2 ");
        sql.append("where t.spot_code = t2.spot_code order by t.count / t.totalCount desc, t.spot_code");


        List<Object[]> list = super.sqlQueryByNativeSql(sql.toString(), param.toArray());
        return list;
    }
}
