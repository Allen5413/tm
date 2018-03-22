package com.zs.service.orderbook.purchaseorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.orderbook.PurchaseOrder;
import com.zs.service.orderbook.purchaseorder.FindPurchaseOrderListByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/12.
 */
@Service("findPurchaseOrderListByWhereService")
public class FindPurchaseOrderListByWhereServiceImpl extends EntityServiceImpl implements FindPurchaseOrderListByWhereService {

    @Resource
    private FindListByWhereDAO findPurchaseOrderListByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONArray getPurchaseOrderListByWhere(String semesterId, String channelId, String tmTypeId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("semesterId", semesterId+"");
        params.put("channelId", channelId+"");
        params.put("tmTypeId", tmTypeId+"");
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("po.code", true);
        sortMap.put("tmt.code", true);
        List<Object[]> resultList = findPurchaseOrderListByWhereDAO.findListByWhere(params, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setCreateTime((Date) objs[8]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(purchaseOrder, jsonConfig);
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("code", objs[0]);
                jsonObject.put("channelId", objs[1]);
                jsonObject.put("channelName", objs[2]);
                jsonObject.put("tmTypeName", objs[3]);
                jsonObject.put("tmCount", null == objs[4] ? 0 : objs[4]);
                jsonObject.put("price", objs[5]);
                jsonObject.put("putCount", null == objs[6] ? 0 : objs[6]);
                jsonObject.put("creator", objs[7]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
