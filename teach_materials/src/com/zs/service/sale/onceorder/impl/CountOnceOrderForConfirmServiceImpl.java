package com.zs.service.sale.onceorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.service.sale.onceorder.CountOnceOrderForConfirmService;
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
 * Created by Allen on 2015/9/4.
 */
@Service("countOnceOrderForConfirm")
public class CountOnceOrderForConfirmServiceImpl extends EntityServiceImpl
        implements CountOnceOrderForConfirmService {

    @Resource
    private FindListByWhereDAO countOnceOrderForConfirmDAO;

    @Override
    @Transactional
    public JSONObject find(Map<String, String> paramsMap) {
        JSONObject json = new JSONObject();
        List<Object[]> resultList = countOnceOrderForConfirmDAO.findListByWhere(paramsMap, null);
        int sumTotalCount = 0;
        int sumNotCount = 0;
        int sumCount = 0;
        BigDecimal sumTotalPrice = new BigDecimal(0);
        BigDecimal sumPrice = new BigDecimal(0);
        String sumTotalCountPercent = "";
        String sumTotalPricePercent = "";

        if(null != resultList && 0 < resultList.size()){
            JSONArray result = new JSONArray();
            for(Object[] objs : resultList){
                int totalCount = Integer.parseInt(objs[2].toString());
                int count = Integer.parseInt(objs[3].toString());
                int notCount = totalCount - count;
                BigDecimal totalPrice = new BigDecimal(objs[4].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal price = new BigDecimal(objs[5].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                String countPercent = new BigDecimal(100 * (float)count / (float)totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                String pricePercent = new BigDecimal(price.floatValue() / totalPrice.floatValue() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spotCode", objs[0]);
                jsonObject.put("spotName", objs[1]);
                jsonObject.put("totalCount", totalCount);
                jsonObject.put("notCount", notCount);
                jsonObject.put("count", count);
                jsonObject.put("totalPrice", StringTools.getFinancePrice(totalPrice.toString()));
                jsonObject.put("price", StringTools.getFinancePrice(price.toString()));
                jsonObject.put("countPercent", countPercent+"%");
                jsonObject.put("pricePercent", pricePercent+"%");
                result.add(jsonObject);

                sumTotalCount += totalCount;
                sumNotCount += notCount;
                sumCount += count;
                sumTotalPrice = sumTotalPrice.add(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                sumPrice = sumPrice.add(price).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            json.put("result", result);

            sumTotalCountPercent = new BigDecimal(100*(float)sumCount/(float)sumTotalCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            sumTotalPricePercent = new BigDecimal(sumPrice.floatValue() / sumTotalPrice.floatValue() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }

        json.put("sumTotalCount", sumTotalCount);
        json.put("sumNotCount", sumNotCount);
        json.put("sumCount", sumCount);
        json.put("sumTotalPrice", StringTools.getFinancePrice(sumTotalPrice.toString()));
        json.put("sumPrice",  StringTools.getFinancePrice(sumPrice.toString()));
        json.put("sumTotalCountPercent", sumTotalCountPercent+"%");
        json.put("sumTotalPricePercent", sumTotalPricePercent+"%");
        return json;
    }
}
