package com.zs.service.basic.stockallotlog.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.domain.basic.StockAllotLog;
import com.zs.service.basic.stockallotlog.FindStockAllotLogListBytmIdService;
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
 * Created by Allen on 2015/5/28.
 */
@Service("findStockAllotLogListBytmIdService")
public class FindStockAllotLogListBytmIdServiceImpl extends EntityServiceImpl
    implements FindStockAllotLogListBytmIdService {

    @Resource
    private FindListByWhereDAO findStockAllotLogListByWhereDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONArray getStockAllotLogListBytmId(Long tmId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("tmId", tmId+"");
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("sal.operate_time", true);
        List<Object[]> resultList = findStockAllotLogListByWhereDAO.findListByWhere(params, sortMap);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                StockAllotLog stockAllotLog = new StockAllotLog();
                stockAllotLog.setOperateTime((Date) objs[4]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(stockAllotLog, jsonConfig);
                jsonObject.put("operateTime", "null".equals(jsonObject.get("operateTime").toString()) ? "" : jsonObject.get("operateTime"));
                jsonObject.put("afterChannelName", objs[0]);
                jsonObject.put("beforeChannelName", objs[1]);
                jsonObject.put("stock", objs[2]);
                jsonObject.put("operator", objs[3]);
                result.add(jsonObject);
            }
            return result;
        }
        return null;
    }
}
