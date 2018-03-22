package com.zs.service.finance.spotexpense.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.finance.spotexpense.SpotExpenseDao;
import com.zs.domain.finance.SpotExpense;
import com.zs.service.finance.spotexpense.FindSpotEPageByWhereService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by LihongZhang on 2015/5/17.
 */
@Service
public class FindSpotEPageByWhereServiceImpl extends EntityServiceImpl implements FindSpotEPageByWhereService {

    @Resource
    private FindPageByWhereDAO findSpotEPageByWhereDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findSpotEPageByWhereDao.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                double pay = null == objs[2] ? 0 : Double.parseDouble(objs[2].toString());
                double buy = null == objs[3] ? 0 : Double.parseDouble(objs[3].toString());
                int discount = null == objs[9] ? 100 : Integer.parseInt(objs[9].toString());
                double actualBuy = new BigDecimal(buy).multiply(new BigDecimal(discount)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();    //实际消费
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spotCode", objs[0]);
                jsonObject.put("spotName", objs[1]);
                jsonObject.put("pay", pay);
                jsonObject.put("buy", buy);
                jsonObject.put("confirmedMoney", objs[4]);
                jsonObject.put("acc", new BigDecimal(pay).subtract(new BigDecimal(actualBuy)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                jsonObject.put("semester", objs[7]+"年"+("0".equals(objs[8].toString()) ? "上半年":"下半年"));
                jsonObject.put("discount", 100 == discount ? "无折扣" : new BigDecimal(discount).divide(new BigDecimal(10)).setScale(1, BigDecimal.ROUND_HALF_UP).toString()+"折");
                jsonObject.put("id", objs[10]);
                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
        }
        return pageInfo;
    }
}
