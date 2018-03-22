package com.zs.service.sale.onceorder.impl;

import com.alibaba.fastjson.JSONArray;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.sale.onceorder.CountOnceOrderForConfirmStudentDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.service.sale.onceorder.CountOnceOrderForConfirmStudentService;
import com.zs.tools.StringTools;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Allen on 2016/11/15.
 */
@Service("countOnceOrderForConfirmStudentService")
public class CountOnceOrderForConfirmStudentServiceImpl extends EntityServiceImpl<StudentBookOnceOrder, CountOnceOrderForConfirmStudentDAO>
    implements CountOnceOrderForConfirmStudentService{

    @Resource
    private CountOnceOrderForConfirmStudentDAO countOnceOrderForConfirmStudentDAO;

    @Override
    public JSONObject find(String spotCode, int flag) {
        JSONObject jsonObject = new JSONObject();
        List<Object[]> list = null;
        if(0 == flag){
            list = countOnceOrderForConfirmStudentDAO.findNotConfirm(spotCode);
        }else{
            list = countOnceOrderForConfirmStudentDAO.findConfirm(spotCode);
        }

        if(null != list && 0 < list.size()){
            BigDecimal sumTotalPrice = new BigDecimal(0);
            BigDecimal sumPrice = new BigDecimal(0);
            String sumTotalPricePercent = "";
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                BigDecimal totalPrice = new BigDecimal(objs[4].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal price = new BigDecimal(objs[5].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                String pricePercent = new BigDecimal(price.floatValue() / totalPrice.floatValue() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                JSONObject json = new JSONObject();
                json.put("code", objs[0]);
                json.put("name", objs[1]);
                json.put("level", LevelEnum.getDescn(objs[2].toString()));
                json.put("spec", SpecEnum.getDescn(objs[3].toString()));
                json.put("totalPrice", totalPrice);
                json.put("price", price);
                json.put("pricePercent", pricePercent+"%");
                jsonArray.add(json);

                sumTotalPrice = sumTotalPrice.add(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                sumPrice = sumPrice.add(price).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            jsonObject.put("result", jsonArray);
            sumTotalPricePercent = new BigDecimal(sumPrice.floatValue() / sumTotalPrice.floatValue() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

            jsonObject.put("sumTotalPrice", StringTools.getFinancePrice(sumTotalPrice.toString()));
            jsonObject.put("sumPrice",  StringTools.getFinancePrice(sumPrice.toString()));
            jsonObject.put("sumTotalPricePercent", sumTotalPricePercent+"%");
        }
        return jsonObject;
    }
}
