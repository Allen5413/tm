package com.zs.dao.orderbook.purchaseorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.dao.BaseQueryDao;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.domain.orderbook.PurchaseOrder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/5.
 */
@Service("findPurchaseOrderPageByWhereDAO")
public class FindPurchaseOrderPageByWhereDAOImpl extends BaseQueryDao
        implements FindPageByWhereDAO {
    @Override
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap) {
        PageInfo orderPageInfo = new PageInfo();
        orderPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        orderPageInfo.setCountOfCurrentPage(pageInfo.getCountOfCurrentPage());

        String field = "t.*";
        StringBuilder sql = new StringBuilder("from (select po.code, ic.id, ic.name as channelName, tmt.name as tmTypeName, sum(potm.teach_material_count) as tmCount, cast(sum(potm.teach_material_count * tm.price) as char) as price, sum(put_storage_count) as put_count, po.creator, po.create_time ");
        sql.append("from purchase_order po, issue_channel ic, teach_material_type tmt, purchase_order_teach_material potm, teach_material tm where po.code = potm.code and po.issue_channel_id = ic.id and po.teach_material_type_id = tmt.id and potm.teach_material_id = tm.id ");
        String semesterId = paramsMap.get("semesterId");
        String channelId = paramsMap.get("channelId");
        String tmTypeId = paramsMap.get("tmTypeId");

        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isEmpty(semesterId)){
            sql.append("and po.semester_id = ? ");
            params.add(semesterId);
        }
        if(!StringUtils.isEmpty(channelId)){
            sql.append("and ic.id = ? ");
            params.add(channelId);
        }
        if(!StringUtils.isEmpty(tmTypeId)){
            sql.append("and tmt.id = ? ");
            params.add(tmTypeId);
        }
        sql.append("group by po.code, ic.name, tmt.name, po.creator, po.create_time ");
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
        super.pageSqlQueryByNativeSql(orderPageInfo, sql.toString(), field, params.toArray());
        return orderPageInfo;
    }
}
