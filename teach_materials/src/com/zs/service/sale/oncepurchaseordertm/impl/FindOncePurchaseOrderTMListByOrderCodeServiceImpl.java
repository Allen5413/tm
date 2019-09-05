package com.zs.service.sale.oncepurchaseordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.orderbook.PurchaseOrderTeachMaterial;
import com.zs.service.orderbook.purchaseorderteachmaterial.FindPurchaseOrderTeachMaterialListByOrderCodeService;
import com.zs.service.sale.oncepurchaseordertm.FindOncePurchaseOrderTMListByOrderCodeService;
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
@Service("findOncePurchaseOrderTMListByOrderCodeService")
public class FindOncePurchaseOrderTMListByOrderCodeServiceImpl
        extends EntityServiceImpl implements FindOncePurchaseOrderTMListByOrderCodeService {

    @Resource
    private FindListByWhereDAO findOncePurchaseOrderTMListByOrderCodeDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONArray find(String orderCode, Long semesterId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderCode", orderCode);
        params.put("semesterId", semesterId+"");
        List<Object[]> resultList = findOncePurchaseOrderTMListByOrderCodeDAO.findListByWhere(params, null);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                PurchaseOrderTeachMaterial purchaseOrderTeachMaterial = new PurchaseOrderTeachMaterial();
                purchaseOrderTeachMaterial.setOperateTime((Date) objs[11]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(purchaseOrderTeachMaterial, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("deptName", objs[0]);
                jsonObject.put("id", objs[1]);
                jsonObject.put("courseCode", objs[2]);
                jsonObject.put("state", objs[3]);
                jsonObject.put("pressName", objs[4]);
                jsonObject.put("isbn", objs[5]);
                jsonObject.put("tmName", objs[6]);
                jsonObject.put("author", objs[7]);
                jsonObject.put("price", objs[8]);
                jsonObject.put("tmCount", objs[9]);
//                jsonObject.put("storageCount", objs[10]);
//                jsonObject.put("stock", objs[11]);
//                jsonObject.put("sCount", objs[12]);
//                jsonObject.put("pCount", objs[13]);
                jsonObject.put("operator", objs[10]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
