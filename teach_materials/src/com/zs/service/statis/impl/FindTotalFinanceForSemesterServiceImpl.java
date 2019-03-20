package com.zs.service.statis.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.statis.FindTotalFinanceForSemesterDAO;
import com.zs.service.statis.FindTotalFinanceForSemesterService;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/1/14.
 */
@Service("findTotalFinanceForSemesterService")
public class FindTotalFinanceForSemesterServiceImpl extends EntityServiceImpl implements FindTotalFinanceForSemesterService {

    @Resource
    private FindTotalFinanceForSemesterDAO findTotalFinanceForSemesterDAO;

    @Override
    @Transactional
    public JSONObject find() throws Exception {
        JSONObject returnJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        BigDecimal totalBuy = new BigDecimal(0);
        BigDecimal totlaPay = new BigDecimal(0);
        BigDecimal totalOwn = new BigDecimal(0);

        List<Object[]> result = findTotalFinanceForSemesterDAO.find();
        if(result != null && 0 < result.size()){
            for(Object[] obj : result){
                JSONObject jsonObject = new JSONObject();
                String year = obj[0]+"年";
                String quarter = 0 == (int)obj[1] ? "上半年" : "下半年";
                double buy = Double.parseDouble(obj[2].toString());
                double pay = Double.parseDouble(obj[3].toString());
                double own = Double.parseDouble(obj[4].toString());

                jsonObject.put("semester", year+quarter);
                jsonObject.put("year", obj[0]);
                jsonObject.put("quarter", obj[1]);
                jsonObject.put("buy", StringTools.getFinancePrice(buy+""));
                jsonObject.put("pay", StringTools.getFinancePrice(pay+""));
                jsonObject.put("own", StringTools.getFinancePrice(own+""));
                jsonArray.add(jsonObject);

                totalBuy = totalBuy.add(new BigDecimal(buy)).setScale(2, BigDecimal.ROUND_HALF_UP);
                totlaPay = totlaPay.add(new BigDecimal(pay)).setScale(2, BigDecimal.ROUND_HALF_UP);
                totalOwn = totalOwn.add(new BigDecimal(own)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }
        returnJSON.put("list", jsonArray);
        returnJSON.put("totalBuy", StringTools.getFinancePrice(totalBuy.toString()));
        returnJSON.put("totalPay", StringTools.getFinancePrice(totlaPay.toString()));
        returnJSON.put("totalOwn", StringTools.getFinancePrice(totalOwn.toString()));
        return returnJSON;
    }

    @Override
    public List<JSONObject> findBySemesterForSpot(int year, int quarter) throws Exception {
        List<JSONObject> resultList = new ArrayList<JSONObject>();
        List<Object[]> list = findTotalFinanceForSemesterDAO.findBySemesterForSpot(year, quarter);
        if(null != list && 0 < list.size()){
            for(Object[] objs : list){
                JSONObject json = new JSONObject();
                json.put("code", objs[0]);
                json.put("name", objs[4]);
                json.put("buy", StringTools.getFinancePrice(objs[1].toString()));
                json.put("pay", StringTools.getFinancePrice(objs[2].toString()));
                json.put("own", StringTools.getFinancePrice(objs[3].toString()));
                resultList.add(json);
            }
        }
        return resultList;
    }
}
