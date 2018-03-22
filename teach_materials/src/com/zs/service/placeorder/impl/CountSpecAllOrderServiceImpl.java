package com.zs.service.placeorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.placeorder.CountSpecAllOrderService;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/12/19.
 */
@Service("countSpecAllOrderService")
public class CountSpecAllOrderServiceImpl extends EntityServiceImpl
        implements CountSpecAllOrderService {

    @Resource
    private FindListByWhereDAO countSpecAllOrderDAO;

    @Override
    @Transactional
    public JSONObject find(Map<String, String> paramsMap) {
        JSONObject json = new JSONObject();
        List<Object[]> resultList = countSpecAllOrderDAO.findListByWhere(paramsMap, null);
        BigDecimal sumTotalPrice = new BigDecimal(0);
        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                BigDecimal totalPrice = new BigDecimal(objs[2].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spotCode", objs[0]);
                jsonObject.put("spotName", objs[1]);
                jsonObject.put("totalPrice", StringTools.getFinancePrice(totalPrice.toString()));
                result.add(jsonObject);
                sumTotalPrice = sumTotalPrice.add(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            json.put("result", result);
        }
        json.put("sumTotalPrice", StringTools.getFinancePrice(sumTotalPrice.toString()));
        return json;
    }
}
