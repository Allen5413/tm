package com.zs.service.orderbook.purchaseorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.orderbook.purchaseorder.PurchaseOrderDAO;
import com.zs.domain.orderbook.PurchaseOrder;
import com.zs.service.orderbook.purchaseorder.FindPurchaseOrderPageByWhereService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/5.
 */
@Service("findPurchaseOrderPageByWhereService")
public class FindPurchaseOrderPageByWhereServiceImpl
        extends EntityServiceImpl<PurchaseOrder, PurchaseOrderDAO>
        implements FindPurchaseOrderPageByWhereService {
    @Resource
    public FindPageByWhereDAO findPurchaseOrderPageByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findPurchaseOrderPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
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
                jsonObject.put("priceStr", StringTools.getFinancePrice(objs[5].toString()));
                jsonObject.put("putCount", null == objs[6] ? 0 : objs[6]);
                jsonObject.put("creator", objs[7]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
