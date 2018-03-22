package com.zs.dao.orderbook.purchaseorderteachmaterial.impl;

import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.BaseQueryDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/12.
 */
@Service("findPurchaseOrderTeachMaterialListByWhereDAO")
public class FindPurchaseOrderTeachMaterialListByWhereDAOImpl extends BaseQueryDao
        implements FindListByWhereDAO {
    @Override
    public List<Object[]> findListByWhere(Map<String, String> paramsMap, Map<String, Boolean> sortMap) {

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select po.code, ic.name as channelName, tmt.name as tmTypeName, sum(potm.teach_material_count) as count, sum(potm.teach_material_count * tm.price) as price, sum(potm.put_storage_count) as storageCount from ");
        sbSql.append("purchase_order po, issue_channel ic, teach_material_type tmt, purchase_order_teach_material potm, teach_material tm where ");
        sbSql.append("po.code = potm.code and po.issue_channel_id = ic.id and po.teach_material_type_id = tmt.id and potm.teach_material_id = tm.id ");

        List<Object> params = new ArrayList<Object>();
        String semesterId = paramsMap.get("semesterId");
        String channelId = paramsMap.get("channelId");
        String tmTypeId = paramsMap.get("tmTypeId");
        if(!StringUtils.isEmpty(semesterId)){
            sbSql.append("and po.semester_id = ? ");
            params.add(semesterId);
        }
        if(!StringUtils.isEmpty(channelId)){
            sbSql.append("and ic.id = ? ");
            params.add(channelId);
        }
        if(!StringUtils.isEmpty(tmTypeId)){
            sbSql.append("and tmt.id = ? ");
            params.add(tmTypeId);
        }
        sbSql.append("group by po.code, ic.name, tmt.name ");
        if(null != sortMap) {
            sbSql.append("order by ");
            int i = 0;
            for (Iterator it = sortMap.keySet().iterator(); it.hasNext(); ) {
                if(0 < i){
                    sbSql.append(",");
                }
                String key = it.next().toString();
                sbSql.append(key);
                sbSql.append(" ");
                sbSql.append(sortMap.get(key) ? "asc" : "desc");
                i++;
            }
        }
        List<Object[]> list = super.sqlQueryByNativeSql(sbSql.toString(), params.toArray());
        return list;
    }
}
