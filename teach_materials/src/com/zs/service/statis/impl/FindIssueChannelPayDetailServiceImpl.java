package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.statis.FindIssueChannelPayDetailService;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/11/25 0025.
 */
@Service("findIssueChannelPayDetailService")
public class FindIssueChannelPayDetailServiceImpl extends EntityServiceImpl implements FindIssueChannelPayDetailService {

    @Resource
    private FindListByWhereDAO findIssueChannelPayDetailDAO;

    @Override
    @Transactional
    public JSONObject findListByWhere(long semesterId, long icId, String type) throws Exception {
        JSONObject resultJSON = new JSONObject();
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("semesterId", semesterId+"");
        paramsMap.put("icId", icId+"");
        paramsMap.put("type", type);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("tt.count", false);
        List<Object[]> resultList = findIssueChannelPayDetailDAO.findListByWhere(paramsMap, sortMap);
        double sumPrice = 0;
        if(null != resultList && 0 < resultList.size()) {
            JSONArray jsonArray = new JSONArray();
            for (Object[] objs : resultList) {
                double price = Double.parseDouble(objs[3].toString());
                int count = Integer.parseInt(objs[4].toString());
                double totalPrice = new BigDecimal(price).multiply(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", objs[0]);
                jsonObject.put("isbn", objs[1]);
                jsonObject.put("author", objs[2]);
                jsonObject.put("price", price);
                jsonObject.put("count", count);
                jsonObject.put("totalPrice", StringTools.getFinancePrice(totalPrice+""));
                jsonArray.add(jsonObject);
                sumPrice = new BigDecimal(sumPrice).add(new BigDecimal(totalPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            resultJSON.put("data", jsonArray);
            resultJSON.put("sumPrice", StringTools.getFinancePrice(sumPrice+""));
        }
        return resultJSON;
    }
}
