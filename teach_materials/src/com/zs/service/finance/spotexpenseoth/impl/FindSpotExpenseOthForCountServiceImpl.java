package com.zs.service.finance.spotexpenseoth.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.finance.studentexpense.SpotPayPolTempDao;
import com.zs.dao.finance.studentexpense.SpotTempPayDao;
import com.zs.domain.finance.Refund;
import com.zs.domain.finance.SpotExpenseOth;
import com.zs.domain.finance.SpotPayPolTemp;
import com.zs.domain.finance.SpotPayTemp;
import com.zs.service.finance.spotexpenseoth.FindSpotExpenseOthForCountService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.StringTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/3/25.
 */
@Service("findSpotExpenseOthForCountService")
public class FindSpotExpenseOthForCountServiceImpl extends EntityServiceImpl implements FindSpotExpenseOthForCountService {

    @Resource
    private FindPageByWhereDAO findSpotExpenseOthForCountDAO;
    @Resource
    private SpotTempPayDao spotTempPayDao;
    @Resource
    private SpotPayPolTempDao spotPayPolTempDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONObject findPageByWhere(PageInfo pageInfo, Map<String, String> map) throws Exception {
        JSONObject returnJSON = new JSONObject();
        pageInfo = findSpotExpenseOthForCountDAO.findPageByWhere(pageInfo, map, null);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();

            BigDecimal sumTotalPay = new BigDecimal(0);
            BigDecimal sumTotalBuy = new BigDecimal(0);
            BigDecimal sumAcc = new BigDecimal(0);
            BigDecimal sumOwn = new BigDecimal(0);
            for(Object[] objs : list){
                BigDecimal totalPay = new BigDecimal(objs[6].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal totalBuy = new BigDecimal(objs[7].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal acc = new BigDecimal(objs[8].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal own = new BigDecimal(objs[9].toString()).setScale(2, BigDecimal.ROUND_HALF_UP);

                sumTotalPay = sumTotalPay.add(totalPay).setScale(2, BigDecimal.ROUND_HALF_UP);
                sumTotalBuy = sumTotalBuy.add(totalBuy).setScale(2, BigDecimal.ROUND_HALF_UP);
                sumAcc = sumAcc.add(acc).setScale(2, BigDecimal.ROUND_HALF_UP);
                sumOwn = sumOwn.add(own).setScale(2, BigDecimal.ROUND_HALF_UP);

                long spotExpenseOthId = Long.parseLong(objs[11].toString());


                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                SpotExpenseOth spotExpenseOth = new SpotExpenseOth();
                spotExpenseOth.setClearTime((Date) objs[10]);
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(spotExpenseOth, jsonConfig);
                jsonObject.put("clearTime", "null".equals(jsonObject.get("clearTime").toString()) ? "" : jsonObject.get("clearTime"));
                jsonObject.put("semesterId", objs[0]);
                jsonObject.put("semesterName", objs[1]+(Integer.parseInt(objs[2].toString()) == 0 ? "春季":"秋季"));
                jsonObject.put("pName", objs[3]);
                jsonObject.put("spotCode", objs[4]);
                jsonObject.put("spotName", objs[5]);
                jsonObject.put("totalPay", StringTools.getFinancePrice(totalPay.toString()));
                jsonObject.put("totalBuy", StringTools.getFinancePrice(totalBuy.toString()));
                jsonObject.put("acc", StringTools.getFinancePrice(acc.toString()));
                jsonObject.put("ownIsRed", own.doubleValue() > 0 ? 1 : 0);
                jsonObject.put("own", StringTools.getFinancePrice(own.toString()));
                jsonObject.put("spotOwnId", spotExpenseOthId);

                //判断是否已经有交费记录，但是还没审核的，如果还未审核，页面就不显示交款按钮
                List<SpotPayTemp> spotPayTemp = spotTempPayDao.querySpotPayTempByOwnId(spotExpenseOthId);
                if(null != spotPayTemp && spotPayTemp.size() == 0){
                    jsonObject.put("showPay",1);
                }else if(null != spotPayTemp && spotPayTemp.size() > 0){
                    List<SpotPayPolTemp> spotPayPolTempList  = spotPayPolTempDao.querySpotPayPolTempByOwnId(spotExpenseOthId);
                    if(null != spotPayPolTempList && spotPayPolTempList.size() > 0){
                        SpotPayPolTemp spotPayPolTemp = spotPayPolTempList.get(0);
                        if(spotPayPolTemp.getIsSure().equals("0")){
                            jsonObject.put("showSur", 0);
                        }else if(spotPayPolTemp.getIsSure().equals("1")){
                            jsonObject.put("showSur", 1);
                        }else if(spotPayPolTemp.getIsSure().equals("2")){
                            jsonObject.put("showSur", 2);
                        }
                    }
                }

                jsonArray.add(jsonObject);
            }
            pageInfo.setPageResults(jsonArray);
            returnJSON.put("pageInfo", pageInfo);
            returnJSON.put("sumTotalPay", StringTools.getFinancePrice(sumTotalPay.toString()));
            returnJSON.put("sumTotalBuy", StringTools.getFinancePrice(sumTotalBuy.toString()));
            returnJSON.put("sumAcc", StringTools.getFinancePrice(sumAcc.toString()));
            returnJSON.put("sumOwn", StringTools.getFinancePrice(sumOwn.toString()));
        }
        return returnJSON;
    }
}
