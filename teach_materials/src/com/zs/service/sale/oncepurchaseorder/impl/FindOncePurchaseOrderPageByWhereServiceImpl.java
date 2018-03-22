package com.zs.service.sale.oncepurchaseorder.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.orderbook.purchaseorder.PurchaseOrderDAO;
import com.zs.dao.sale.oncepurchaseorder.OncePurchaseOrderDAO;
import com.zs.domain.orderbook.PurchaseOrder;
import com.zs.domain.sale.OncePurchaseOrder;
import com.zs.service.orderbook.purchaseorder.FindPurchaseOrderPageByWhereService;
import com.zs.service.sale.oncepurchaseorder.FindOncePurchaseOrderPageByWhereService;
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
@Service("findOncePurchaseOrderPageByWhereService")
public class FindOncePurchaseOrderPageByWhereServiceImpl
        extends EntityServiceImpl<OncePurchaseOrder, OncePurchaseOrderDAO>
        implements FindOncePurchaseOrderPageByWhereService {
    @Resource
    public FindPageByWhereDAO findOncePurchaseOrderPageByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findOncePurchaseOrderPageByWhereDAO.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                OncePurchaseOrder oncePurchaseOrder = new OncePurchaseOrder();
                oncePurchaseOrder.setCreateTime((Date) objs[9]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(oncePurchaseOrder, jsonConfig);
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("code", objs[0]);
                jsonObject.put("channelId", objs[1]);
                jsonObject.put("channelName", objs[2]);
                jsonObject.put("tmTypeName", objs[3]);
                jsonObject.put("tmCount", null == objs[4] ? 0 : objs[4]);
                jsonObject.put("price", objs[5]);
                jsonObject.put("priceStr", StringTools.getFinancePrice(objs[5].toString()));
                jsonObject.put("putCount", null == objs[6] ? 0 : objs[6]);
                jsonObject.put("state", objs[7]);
                jsonObject.put("creator", objs[8]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
