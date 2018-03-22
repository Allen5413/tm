package com.zs.service.finance.studentexpense.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.finance.studentexpense.StudentExpenseDao;
import com.zs.domain.finance.StudentExpense;
import com.zs.service.finance.studentexpense.FindStuEPageByWhereService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 实现分页查询学生费用接口
 * Created by LihongZhang on 2015/5/15.
 */
@Service("findStuEPageByWhereService")
public class FindStuEPageByWhereServiceImpl extends EntityServiceImpl implements FindStuEPageByWhereService {

    @Resource
    private FindPageByWhereDAO findStuEPageByWhereDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap) throws Exception {
        JSONObject returnJSON = new JSONObject();
        pageInfo = findStuEPageByWhereDao.findPageByWhere(pageInfo, map, sortMap);
        double totalPayPrice = 0;
        double totalBuyPrice = 0;
        double totalAccPrice = 0;
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("provCode", objs[0]);
                jsonObject.put("provName", objs[1]);
                jsonObject.put("spotCode", objs[2]);
                jsonObject.put("spotName", objs[3]);
                jsonObject.put("code", objs[4]);
                jsonObject.put("name", objs[5]);
                jsonObject.put("specName", SpecEnum.getDescn(objs[6]+""));
                jsonObject.put("levelName", LevelEnum.getDescn(objs[7]+""));
                jsonObject.put("pay", objs[8]);
                jsonObject.put("buy", objs[9]);
                jsonObject.put("acc", new BigDecimal((Double)objs[8]).subtract(new BigDecimal((Double)objs[9])).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
                jsonArray.add(jsonObject);
                totalPayPrice = new BigDecimal(totalPayPrice).add(new BigDecimal(objs[8].toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                totalBuyPrice = new BigDecimal(totalBuyPrice).add(new BigDecimal(objs[9].toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                totalAccPrice = new BigDecimal(totalAccPrice).add(new BigDecimal(objs[8].toString()).subtract(new BigDecimal(objs[9].toString()))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            pageInfo.setPageResults(jsonArray);
        }
        returnJSON.put("pageInfo", pageInfo);
        returnJSON.put("totalPayPrice", totalPayPrice);
        returnJSON.put("totalBuyPrice", totalBuyPrice);
        returnJSON.put("totalAccPrice", totalAccPrice);
        return returnJSON;
    }
}
