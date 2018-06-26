package com.zs.service.finance.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.dao.finance.FindSpotFinanceTotalDAO;
import com.zs.dao.finance.spotexpense.SpotExpenseDao;
import com.zs.dao.finance.studentexpense.CenterPayDao;
import com.zs.dao.finance.studentexpense.SpotPayPolTempDao;
import com.zs.dao.sync.spot.FindSpotByCodeDAO;
import com.zs.domain.finance.SpotExpense;
import com.zs.domain.finance.SpotPayPolTemp;
import com.zs.domain.sync.Spot;
import com.zs.service.finance.SpotFinanceTotalService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.DateTools;
import com.zs.tools.ExcelTools;
import com.zs.tools.StringTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Allen on 2015/12/7.
 */
@Service("spotFinanceTotalService")
public class SpotFinanceTotalServiceImpl extends EntityServiceImpl<SpotExpense, SpotExpenseDao> implements SpotFinanceTotalService {

    @Resource
    private FindSpotByCodeDAO findSpotByCodeDAO;
    @Resource
    private FindPageByWhereDAO findSpotEPageByWhereDao;
    @Resource
    private FindSpotFinanceTotalDAO findSpotFinanceTotalDAO;
    @Resource
    private FindPageByWhereDAO findSpotExpenseOthForCountDAO;
    @Resource
    private SpotPayPolTempDao spotPayPolTempDao;

    @Override
    @Transactional
    public JSONObject findSpotFinanceTotal(String spotCode) throws Exception {
        JSONObject returnJSON = new JSONObject();
        //查询学习中心信息
        Spot spot = findSpotByCodeDAO.getSpotByCode(spotCode);
        if(null == spot){
            throw new BusinessException("没有找到中心信息");
        }
        returnJSON.put("spotCode", spotCode);
        returnJSON.put("spotName", spot.getName());

        //计算学生个人账户信息
        double studentOwn = 0;
        double studentAcc = 0;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCountOfCurrentPage(9999);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("spotId", spotCode);
        pageInfo = findSpotExpenseOthForCountDAO.findPageByWhere(pageInfo, paramMap, null);
        if(null != pageInfo) {
            List<Object[]> datList = pageInfo.getPageResults();
            if (null != datList && datList.size() > 0) {
                for(Object[] objArr : datList){
                    studentOwn = new BigDecimal(studentOwn).add(new BigDecimal(objArr[9]+"")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    studentAcc = new BigDecimal(studentAcc).add(new BigDecimal(objArr[8]+"")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }
        }
        returnJSON.put("studentOwn", StringTools.getFinancePrice(studentOwn+""));
        returnJSON.put("studentAcc", StringTools.getFinancePrice(studentAcc+""));

        //查询是否存在中心账户交费未审核记录
        List<SpotPayPolTemp> spotPayPolTempList = spotPayPolTempDao.querySpotPayPolTempBySpotCodeForIsSureWait(spotCode);
        if(null != spotPayPolTempList && 0 < spotPayPolTempList.size()){
            returnJSON.put("isExistsWait", 1);
        }else{
            returnJSON.put("isExistsWait", 0);
        }

        //计算学习中心账户余额
        double spotAcc = 0;
        PageInfo pageInfo2 = new PageInfo();
        pageInfo2.setCountOfCurrentPage(9999);
        Map<String, String> paramMap2 = new HashMap<String, String>();
        paramMap2.put("spotCode", spotCode);
        pageInfo2 = findSpotEPageByWhereDao.findPageByWhere(pageInfo2, paramMap2, null);
        if(null != pageInfo2 && null != pageInfo2.getPageResults() && 0 < pageInfo2.getPageResults().size()){
            List<Object[]> list = pageInfo2.getPageResults();
            for(Object[] objs : list){
                String pay = null == objs[2] ? "0" : objs[2]+"";
                String buy = null == objs[3] ? "0" : objs[3]+"";
                String discount = null == objs[9] ? "100" : objs[9]+""; //折扣
                float actualBuy = new BigDecimal(buy).multiply(new BigDecimal(discount)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();    //实际消费
                spotAcc = new BigDecimal(spotAcc).add(new BigDecimal(pay)).subtract(new BigDecimal(actualBuy)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        returnJSON.put("isRedForSpotAcc", spotAcc < 0 ? 0 : 1);
        returnJSON.put("spotAcc", StringTools.getFinancePrice(spotAcc+""));

        //查询教材订购情况
        List<Object[]> tmOrderList = findSpotFinanceTotalDAO.findTMOrderInfo(spotCode);
        JSONArray jsonArray = new JSONArray();
        double sumTotalPrice = 0, sumOwn = 0;
        if(null != tmOrderList && 0 < tmOrderList.size()){
            for(Object[] objs : tmOrderList){
                double totalPrice = Double.parseDouble(objs[3].toString());
                double own = Double.parseDouble(objs[4].toString());
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                SpotExpense spotExpense = new SpotExpense();
                spotExpense.setClearTime(null == objs[5] ? null : DateTools.getFormatDate(objs[5]+"", DateTools.longDatePattern));
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(spotExpense, jsonConfig);
                jsonObject.put("semesterId", objs[0]);
                jsonObject.put("semesterStr", objs[1]+"年"+(0 == objs[2] ? "上学期":"下学期"));
                jsonObject.put("totalPrice", StringTools.getFinancePrice(totalPrice+""));
                jsonObject.put("own", StringTools.getFinancePrice(own+""));
                //如果有欠款
                if(own > 0){
                    jsonObject.put("clearTime", "");
                }else{
                    //没有欠款
                    jsonObject.put("clearTime", "null".equals(jsonObject.get("clearTime").toString()) ? "" : jsonObject.get("clearTime"));
                }
                jsonObject.put("isRed", own > 0 ? 0 : 1);
                jsonArray.add(jsonObject);

                sumTotalPrice = new BigDecimal(sumTotalPrice).add(new BigDecimal(totalPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                sumOwn = new BigDecimal(sumOwn).add(new BigDecimal(own)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        returnJSON.put("tmOrderData", jsonArray);
        returnJSON.put("sumTotalPrice", StringTools.getFinancePrice(sumTotalPrice+""));
        returnJSON.put("sumOwn", StringTools.getFinancePrice(sumOwn+""));

        //查询学生账户进出帐明细
        List<Object[]> studentExpenseList = findSpotFinanceTotalDAO.findStudentExpenseInfo(spotCode);
        JSONArray jsonArray2 = new JSONArray();
        if(null != studentExpenseList && 0 < studentExpenseList.size()){
            for(Object[] objs : studentExpenseList){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                SpotExpense spotExpense = new SpotExpense();
                spotExpense.setCreateTime(null == objs[0] ? null : DateTools.getFormatDate(objs[0] + "", DateTools.longDatePattern));
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(spotExpense, jsonConfig);
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("code", objs[1]);
                jsonObject.put("name", objs[2]);
                jsonObject.put("money", objs[3]);
                jsonObject.put("detail", objs[4]);
                jsonArray2.add(jsonObject);
            }
        }
        returnJSON.put("studentExpenseDate", jsonArray2);

        //查询学习中心账户进出帐明细
        List<Object[]> spotExpenseList = findSpotFinanceTotalDAO.findSpotExpenseInfo(spotCode);
        JSONArray jsonArray3 = new JSONArray();
        if(null != spotExpenseList && 0 < spotExpenseList.size()){
            for(Object[] objs : spotExpenseList){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                SpotExpense spotExpense = new SpotExpense();
                spotExpense.setCreateTime(null == objs[0] ? null : DateTools.getFormatDate(objs[0] + "", DateTools.longDatePattern));
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(spotExpense, jsonConfig);
                jsonObject.put("createTime", "null".equals(jsonObject.get("createTime").toString()) ? "" : jsonObject.get("createTime"));
                jsonObject.put("creator", objs[1]);
                jsonObject.put("money", objs[2]);
                jsonObject.put("detail", objs[3]);
                jsonArray3.add(jsonObject);
            }
        }
        returnJSON.put("spotExpenseDate", jsonArray3);

        return returnJSON;
    }

    /**
     * 查询学习中心一个学期的教材订购情况
     * @param spotCode
     * @param semesterId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public JSONObject findSpotOrderTMInfo(String spotCode, long semesterId) throws Exception {
        JSONObject returnJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            List<Object[]> list = findSpotFinanceTotalDAO.findSpotOrderTMInfo(spotCode, semesterId);
            double sumTotalPrice = 0;
            if(null != list && 0 < list.size()){
                for(Object[] objs : list){
                    double totalPrice = null == objs[7] ? 0 : Double.parseDouble(objs[7].toString());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("address", null == objs[0] ? "" : objs[0]);
                    jsonObject.put("adminName", null == objs[1] ? "" : objs[1]);
                    jsonObject.put("name", objs[2]);
                    jsonObject.put("isbn", objs[3]);
                    jsonObject.put("author", objs[4]);
                    jsonObject.put("price", objs[5]);
                    jsonObject.put("count", objs[6]);
                    jsonObject.put("totalPrice", objs[7]);
                    jsonArray.add(jsonObject);
                    sumTotalPrice = new BigDecimal(sumTotalPrice).add(new BigDecimal(totalPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }
            returnJSON.put("sumTotalPrice", sumTotalPrice);
            returnJSON.put("data", jsonArray);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnJSON;
    }

    @Override
    public String downSpotOrderTMInfo(String spotCode, long semesterId, String fileName) throws Exception {
        JSONObject jsonObject = this.findSpotOrderTMInfo(spotCode, semesterId);
        return this.createExcel(jsonObject, fileName);
    }

    /**
     * 查询有欠费的学生信息
     * @param spotCode
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public JSONArray findStudentOwnInfo(String spotCode) throws Exception {
        JSONArray jsonArray = new JSONArray();
        try{
            List<Object[]> list = findSpotFinanceTotalDAO.findStudentOwnInfo(spotCode);
            if(null != list && 0 < list.size()){
                for(Object[] objs : list){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code", objs[0]);
                    jsonObject.put("name", objs[1]);
                    jsonObject.put("spec", SpecEnum.getDescn(objs[2].toString()));
                    jsonObject.put("level", LevelEnum.getDescn(objs[3].toString()));
                    jsonObject.put("pay", objs[4]);
                    jsonObject.put("buy", objs[5]);
                    jsonObject.put("own", new BigDecimal(objs[5]+"").subtract(new BigDecimal(objs[4]+"")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    jsonArray.add(jsonObject);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 查询有余额的学生信息
     * @param spotCode
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public JSONArray findStudentAccInfo(String spotCode) throws Exception {
        JSONArray jsonArray = new JSONArray();
        try{
            List<Object[]> list = findSpotFinanceTotalDAO.findStudentAccInfo(spotCode);
            if(null != list && 0 < list.size()){
                for(Object[] objs : list){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code", objs[0]);
                    jsonObject.put("name", objs[1]);
                    jsonObject.put("spec", SpecEnum.getDescn(objs[2].toString()));
                    jsonObject.put("level", LevelEnum.getDescn(objs[3].toString()));
                    jsonObject.put("pay", objs[4]);
                    jsonObject.put("buy", objs[5]);
                    jsonObject.put("acc", new BigDecimal(objs[4]+"").subtract(new BigDecimal(objs[5]+"")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    jsonArray.add(jsonObject);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }


    public List<JSONObject> countReward(int year, String spotCode)throws Exception{
        List<JSONObject> jsonList = new ArrayList<JSONObject>();
        List<Object[]> list = null;
        if(StringUtils.isEmpty(spotCode)) {
            list = findSpotFinanceTotalDAO.countReward(year);
        }else{
            list = findSpotFinanceTotalDAO.countReward(year, spotCode);
        }
        if(null != list && 0 < list.size()){
            for(Object[] objs : list){
                String clearTime = null == objs[4] ? null : objs[4].toString();
                int year2 = Integer.parseInt(objs[5].toString());
                int quarter = Integer.parseInt(objs[6].toString());
                String rewardMoney = "0";
                float buy = Float.parseFloat(objs[2].toString());

                SpotExpense spotExpense = new SpotExpense();
                if(null != clearTime) {
                    spotExpense.setClearTime(DateTools.getFormatDate(clearTime, DateTools.longDatePattern));
                }
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                JSONObject jsonObject = JSONObject.fromObject(spotExpense, jsonConfig);
                jsonObject.put("clearTime", "null".equals(jsonObject.get("clearTime").toString()) ? "" : jsonObject.get("clearTime"));
                jsonObject.put("semester", (quarter == 0 ? "上学期" : "下学期"));
                jsonObject.put("spotCode", objs[1]);
                jsonObject.put("spotName", "["+objs[1]+"]"+objs[7]);
                jsonObject.put("buy", StringTools.getFinancePrice(buy+""));
                int ratio = 0;
                if(quarter == 0){
                    //春季学期
                    String date1 = year+"-07-15 23:59:59";
                    String date2 = year+"-09-15 23:59:59";
                    if(null != clearTime){
                        if (DateTools.transferStringToLong(clearTime) <= DateTools.transferStringToLong(date1)) {
                            rewardMoney = buy * 0.05 + "";
                            ratio = 5;
                        }
                        if (DateTools.transferStringToLong(date1) < DateTools.transferStringToLong(clearTime) &&
                                DateTools.transferStringToLong(clearTime) <= DateTools.transferStringToLong(date2)) {
                            rewardMoney = buy * 0.04 + "";
                            ratio = 4;
                        }
                    }
                }else{
                    //秋季学期
                    String date1 = (year+1)+"-01-15 23:59:59";
                    String date2 = (year+1)+"-03-15 23:59:59";
                    if(null != clearTime) {
                        if (DateTools.transferStringToLong(clearTime) <= DateTools.transferStringToLong(date1)) {
                            rewardMoney = buy * 0.05 + "";
                            ratio = 5;
                        }
                        if (DateTools.transferStringToLong(date1) < DateTools.transferStringToLong(clearTime) &&
                                DateTools.transferStringToLong(clearTime) <= DateTools.transferStringToLong(date2)) {
                            rewardMoney = buy * 0.04 + "";
                            ratio = 4;
                        }
                    }
                }
                jsonObject.put("ratio", ratio+"%");
                jsonObject.put("rewardMoney", new BigDecimal(rewardMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
                jsonList.add(jsonObject);
            }
        }
        return this.mergeRewardData(jsonList, year);
    }

    /**
     * 把上下学期的2条数据合并成1条数据
     * @param list
     * @return
     */
    private List<JSONObject> mergeRewardData(List<JSONObject> list, int year) throws Exception {
        List<JSONObject> returnList = new ArrayList<JSONObject>();
        if(null != list){
            String beforeSpotCode = "";
            JSONObject jsonObject = new JSONObject();
            BigDecimal rewardMoneyTotal = new BigDecimal(0);
            int i=0;
            for(JSONObject json : list){
                String spotCode = json.get("spotCode").toString();
                String spotName = json.get("spotName").toString();
                if(!beforeSpotCode.equals(spotCode)){
                    if(i > 0) {
                        jsonObject.put("rewardMoneyTotal", rewardMoneyTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
                        //查询奖励日期比例
                        List<Object[]> list2 = findSpotFinanceTotalDAO.countRewardForDate(year, beforeSpotCode, year+"-07-15 23:59:59", year+"-09-15 23:59:59", (year+1)+"-01-15 23:59:59", (1+year)+"-03-15 23:59:59");
                        if(null != list2 && 0 < list2.size()){
                            if(1 == list2.size()){
                                int quarter = (Integer)list2.get(0)[0];
                                if(quarter == 0){
                                    String pay  = null == list2.get(0)[1] ? "0" : list2.get(0)[1].toString();
                                    String pay2  = null == list2.get(0)[2] ? "0" : list2.get(0)[2].toString();
                                    String pay3  = null == list2.get(0)[3] ? "0" : list2.get(0)[3].toString();
                                    jsonObject.put("pay", StringTools.getFinancePrice(pay));
                                    jsonObject.put("pay2", StringTools.getFinancePrice(pay2));
                                    jsonObject.put("pay3", StringTools.getFinancePrice(pay3));
                                    jsonObject.put("pay4", "0");
                                    jsonObject.put("pay5", "0");
                                    jsonObject.put("pay6", "0");
                                }else{
                                    String pay4  = null == list2.get(0)[4] ? "0" : list2.get(0)[4].toString();
                                    String pay5  = null == list2.get(0)[5] ? "0" : list2.get(0)[5].toString();
                                    String pay6  = null == list2.get(0)[6] ? "0" : list2.get(0)[6].toString();
                                    jsonObject.put("pay", "0");
                                    jsonObject.put("pay2", "0");
                                    jsonObject.put("pay3", "0");
                                    jsonObject.put("pay4", StringTools.getFinancePrice(pay4));
                                    jsonObject.put("pay5", StringTools.getFinancePrice(pay5));
                                    jsonObject.put("pay6", StringTools.getFinancePrice(pay6));
                                }
                            }else{
                                String pay  = null == list2.get(0)[1] ? "0" : list2.get(0)[1].toString();
                                String pay2  = null == list2.get(0)[2] ? "0" : list2.get(0)[2].toString();
                                String pay3  = null == list2.get(0)[3] ? "0" : list2.get(0)[3].toString();
                                String pay4  = null == list2.get(1)[4] ? "0" : list2.get(1)[4].toString();
                                String pay5  = null == list2.get(1)[5] ? "0" : list2.get(1)[5].toString();
                                String pay6  = null == list2.get(1)[6] ? "0" : list2.get(1)[6].toString();
                                jsonObject.put("pay", StringTools.getFinancePrice(pay));
                                jsonObject.put("pay2", StringTools.getFinancePrice(pay2));
                                jsonObject.put("pay3", StringTools.getFinancePrice(pay3));
                                jsonObject.put("pay4", StringTools.getFinancePrice(pay4));
                                jsonObject.put("pay5", StringTools.getFinancePrice(pay5));
                                jsonObject.put("pay6", StringTools.getFinancePrice(pay6));
                            }
                        }
                        returnList.add(jsonObject);
                        jsonObject = new JSONObject();
                        rewardMoneyTotal = new BigDecimal(0);
                    }
                    jsonObject.put("spotCode", spotCode);
                    jsonObject.put("spotName", spotName);
                    beforeSpotCode = spotCode;
                }
                if(json.get("semester").toString().indexOf("上学期") > -1){
                    jsonObject.put("semester", json.get("semester"));
                    jsonObject.put("buy", json.get("buy"));
                    jsonObject.put("ratio", json.get("ratio"));
                    jsonObject.put("rewardMoney", StringTools.getFinancePrice(json.get("rewardMoney").toString()));
                    jsonObject.put("clearTime", json.get("clearTime"));
                }else{
                    jsonObject.put("semester2", json.get("semester"));
                    jsonObject.put("buy2", json.get("buy"));
                    jsonObject.put("ratio2", json.get("ratio"));
                    jsonObject.put("rewardMoney2", StringTools.getFinancePrice(json.get("rewardMoney").toString()));
                    jsonObject.put("clearTime2", json.get("clearTime"));
                }
                rewardMoneyTotal = rewardMoneyTotal.add(new BigDecimal(json.get("rewardMoney").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
                if(i == list.size()-1){
                    jsonObject.put("rewardMoneyTotal", rewardMoneyTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
                    //查询奖励日期比例
                    List<Object[]> list2 = findSpotFinanceTotalDAO.countRewardForDate(year, spotCode, year+"-07-15 23:59:59", year+"-09-15 23:59:59", (year+1)+"-01-15 23:59:59", (1+year)+"-03-15 23:59:59");
                    if(null != list2 && 0 < list2.size()) {
                        if (1 == list2.size()) {
                            int quarter = (Integer) list2.get(0)[0];
                            if (quarter == 0) {
                                String pay = null == list2.get(0)[1] ? "0" : list2.get(0)[1].toString();
                                String pay2 = null == list2.get(0)[2] ? "0" : list2.get(0)[2].toString();
                                String pay3 = null == list2.get(0)[3] ? "0" : list2.get(0)[3].toString();
                                jsonObject.put("pay", StringTools.getFinancePrice(pay));
                                jsonObject.put("pay2", StringTools.getFinancePrice(pay2));
                                jsonObject.put("pay3", StringTools.getFinancePrice(pay3));
                                jsonObject.put("pay4", "0");
                                jsonObject.put("pay5", "0");
                                jsonObject.put("pay6", "0");
                            } else {
                                String pay4 = null == list2.get(0)[4] ? "0" : list2.get(0)[4].toString();
                                String pay5 = null == list2.get(0)[5] ? "0" : list2.get(0)[5].toString();
                                String pay6 = null == list2.get(0)[6] ? "0" : list2.get(0)[6].toString();
                                jsonObject.put("pay", "0");
                                jsonObject.put("pay2", "0");
                                jsonObject.put("pay3", "0");
                                jsonObject.put("pay4", StringTools.getFinancePrice(pay4));
                                jsonObject.put("pay5", StringTools.getFinancePrice(pay5));
                                jsonObject.put("pay6", StringTools.getFinancePrice(pay6));
                            }

                        } else {
                            String pay = null == list2.get(0)[1] ? "0" : list2.get(0)[1].toString();
                            String pay2 = null == list2.get(0)[2] ? "0" : list2.get(0)[2].toString();
                            String pay3 = null == list2.get(0)[3] ? "0" : list2.get(0)[3].toString();
                            String pay4 = null == list2.get(1)[4] ? "0" : list2.get(1)[4].toString();
                            String pay5 = null == list2.get(1)[5] ? "0" : list2.get(1)[5].toString();
                            String pay6 = null == list2.get(1)[6] ? "0" : list2.get(1)[6].toString();
                            jsonObject.put("pay", StringTools.getFinancePrice(pay));
                            jsonObject.put("pay2", StringTools.getFinancePrice(pay2));
                            jsonObject.put("pay3", StringTools.getFinancePrice(pay3));
                            jsonObject.put("pay4", StringTools.getFinancePrice(pay4));
                            jsonObject.put("pay5", StringTools.getFinancePrice(pay5));
                            jsonObject.put("pay6", StringTools.getFinancePrice(pay6));
                        }
                    }
                    returnList.add(jsonObject);
                }
                i++;
            }
        }
        return returnList;
    }

    @Override
    public String downReward(int year, String spotCode, String fileName) throws Exception {
        List<JSONObject> list = this.countReward(year, spotCode);
        return this.createExcel2(list, fileName);
    }

    private String createExcel(JSONObject resultJSON, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        JSONArray data = (JSONArray) resultJSON.get("data");
        double sumTotalPrice = (Double) resultJSON.get("sumTotalPrice");
        if(null != data && data.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "邮寄地址",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "收件人",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "教材名称",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "ISBN",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "作者",false,"",30);
            ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "单价",false,"",30);
            ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "数量",false,"",30);
            ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "总价",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            runRow.addExcelCell(runCell_7);
            runRow.addExcelCell(runCell_8);
            sheet.addExcelRow(runRow);
            index ++;

            for(int i = 0; i < data.size(); i++){
                JSONObject jsonObject = (JSONObject)data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);

                ExcelCell runCell_10 = new ExcelCell(index,(byte)0, jsonObject.get("address").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)1, jsonObject.get("adminName").toString(),false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)2, jsonObject.get("name").toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)3, jsonObject.get("isbn").toString(),false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)4, jsonObject.get("author").toString(),false,"",9);
                ExcelCell runCell_15 = new ExcelCell(index,(byte)5, jsonObject.get("price").toString(),false,"",9);
                ExcelCell runCell_16 = new ExcelCell(index,(byte)6, jsonObject.get("count").toString(),false,"",9);
                ExcelCell runCell_17 = new ExcelCell(index,(byte)7, jsonObject.get("totalPrice").toString(),false,"",9);


                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);
                runRow_2.addExcelCell(runCell_12);
                runRow_2.addExcelCell(runCell_13);
                runRow_2.addExcelCell(runCell_14);
                runRow_2.addExcelCell(runCell_15);
                runRow_2.addExcelCell(runCell_16);
                runRow_2.addExcelCell(runCell_17);

                sheet.addExcelRow(runRow_2);

                index ++;
            }

            ExcelRow runRow_3 = new ExcelRow(index);

            ExcelCell runCell_18 = new ExcelCell(index,(byte)6, "合计：",false,"",9);
            ExcelCell runCell_19 = new ExcelCell(index,(byte)7, sumTotalPrice+"",false,"",9);

            runRow_3.addExcelCell(runCell_18);
            runRow_3.addExcelCell(runCell_19);

            sheet.addExcelRow(runRow_3);
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)20000);
        columnWidthMap.put((short)1, (short)5000);
        columnWidthMap.put((short)2, (short)10000);
        columnWidthMap.put((short)3, (short)7000);
        columnWidthMap.put((short)4, (short)10000);
        columnWidthMap.put((short)5, (short)5000);
        columnWidthMap.put((short)6, (short)5000);
        columnWidthMap.put((short)7, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }

    private String createExcel2(List<JSONObject> data, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        if(null != data && data.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "学习中心",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "学期",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "消费",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "比例",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "奖励",false,"",30);
            ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "费用结清时间",false,"",30);
            ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "学期",false,"",30);
            ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "消费",false,"",30);
            ExcelCell runCell_9 = new ExcelCell(index,(byte)8, "比例",false,"",30);
            ExcelCell runCell_10 = new ExcelCell(index,(byte)9, "奖励",false,"",30);
            ExcelCell runCell_11 = new ExcelCell(index,(byte)10, "费用结清时间",false,"",30);
            ExcelCell runCell_12 = new ExcelCell(index,(byte)11, "合计",false,"",30);
            ExcelCell runCell_13 = new ExcelCell(index,(byte)12, "开户行",false,"",30);
            ExcelCell runCell_14 = new ExcelCell(index,(byte)13, "开户名",false,"",30);
            ExcelCell runCell_15 = new ExcelCell(index,(byte)14, "卡号",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            runRow.addExcelCell(runCell_7);
            runRow.addExcelCell(runCell_8);
            runRow.addExcelCell(runCell_9);
            runRow.addExcelCell(runCell_10);
            runRow.addExcelCell(runCell_11);
            runRow.addExcelCell(runCell_12);
            runRow.addExcelCell(runCell_13);
            runRow.addExcelCell(runCell_14);
            runRow.addExcelCell(runCell_15);
            sheet.addExcelRow(runRow);
            index ++;

            BigDecimal sumTotalPrice = new BigDecimal(0);
            for(int i = 0; i < data.size(); i++){
                JSONObject jsonObject = data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);

                BigDecimal rewardMoneyTotal = new BigDecimal(jsonObject.get("rewardMoneyTotal").toString());
                sumTotalPrice = sumTotalPrice.add(rewardMoneyTotal).setScale(2, BigDecimal.ROUND_HALF_UP);

                ExcelCell runCell_16 = new ExcelCell(index,(byte)0, "("+jsonObject.get("spotCode").toString()+")"+jsonObject.get("spotName").toString(),false,"",9);
                ExcelCell runCell_17 = new ExcelCell(index,(byte)1, null == jsonObject.get("semester") || StringUtils.isEmpty(jsonObject.get("semester").toString()) || "null".equals(jsonObject.get("semester").toString()) ? "上学期" : jsonObject.get("semester").toString(),false,"",9);
                ExcelCell runCell_18 = new ExcelCell(index,(byte)2, null == jsonObject.get("buy") || StringUtils.isEmpty(jsonObject.get("buy").toString()) || "null".equals(jsonObject.get("buy").toString()) ? "0" : jsonObject.get("buy").toString(),false,"",9);
                ExcelCell runCell_19 = new ExcelCell(index,(byte)3, null == jsonObject.get("ratio") || StringUtils.isEmpty(jsonObject.get("ratio").toString()) || "null".equals(jsonObject.get("ratio").toString()) ? "0%" : jsonObject.get("ratio").toString(),false,"",9);
                ExcelCell runCell_20 = new ExcelCell(index,(byte)4, null == jsonObject.get("rewardMoney") || StringUtils.isEmpty(jsonObject.get("rewardMoney").toString()) || "null".equals(jsonObject.get("rewardMoney").toString()) ? "0" : jsonObject.get("rewardMoney").toString(),false,"",9);
                ExcelCell runCell_21 = new ExcelCell(index,(byte)5, null == jsonObject.get("clearTime") || StringUtils.isEmpty(jsonObject.get("clearTime").toString()) || "null".equals(jsonObject.get("clearTime").toString()) ? "" : jsonObject.get("clearTime").toString(),false,"",9);
                ExcelCell runCell_22 = new ExcelCell(index,(byte)6, null == jsonObject.get("semester2") || StringUtils.isEmpty(jsonObject.get("semester2").toString()) || "null".equals(jsonObject.get("semester2").toString()) ? "下学期" : jsonObject.get("semester2").toString(),false,"",9);
                ExcelCell runCell_23 = new ExcelCell(index,(byte)7, null == jsonObject.get("buy2") || StringUtils.isEmpty(jsonObject.get("buy2").toString()) || "null".equals(jsonObject.get("buy2").toString()) ? "0" : jsonObject.get("buy2").toString(),false,"",9);
                ExcelCell runCell_24 = new ExcelCell(index,(byte)8, null == jsonObject.get("ratio2") || StringUtils.isEmpty(jsonObject.get("ratio2").toString()) || "null".equals(jsonObject.get("ratio2").toString()) ? "0%" : jsonObject.get("ratio2").toString(),false,"",9);
                ExcelCell runCell_25 = new ExcelCell(index,(byte)9, null == jsonObject.get("rewardMoney2") || StringUtils.isEmpty(jsonObject.get("rewardMoney2").toString()) || "null".equals(jsonObject.get("rewardMoney2").toString()) ? "0" : jsonObject.get("rewardMoney2").toString(),false,"",9);
                ExcelCell runCell_26 = new ExcelCell(index,(byte)10, null == jsonObject.get("clearTime2") || StringUtils.isEmpty(jsonObject.get("clearTime2").toString()) || "null".equals(jsonObject.get("clearTime2").toString()) ? "" : jsonObject.get("clearTime2").toString(),false,"",9);
                ExcelCell runCell_27 = new ExcelCell(index,(byte)11, rewardMoneyTotal.toString(),false,"",9);

                runRow_2.addExcelCell(runCell_16);
                runRow_2.addExcelCell(runCell_17);
                runRow_2.addExcelCell(runCell_18);
                runRow_2.addExcelCell(runCell_19);
                runRow_2.addExcelCell(runCell_20);
                runRow_2.addExcelCell(runCell_21);
                runRow_2.addExcelCell(runCell_22);
                runRow_2.addExcelCell(runCell_23);
                runRow_2.addExcelCell(runCell_24);
                runRow_2.addExcelCell(runCell_25);
                runRow_2.addExcelCell(runCell_26);
                runRow_2.addExcelCell(runCell_27);

                sheet.addExcelRow(runRow_2);

                index ++;
            }

            ExcelRow runRow_3 = new ExcelRow(index);

            ExcelCell runCell_28 = new ExcelCell(index,(byte)10, "合计：",false,"",9);
            ExcelCell runCell_29 = new ExcelCell(index,(byte)11, sumTotalPrice.toString(),false,"",9);

            runRow_3.addExcelCell(runCell_28);
            runRow_3.addExcelCell(runCell_29);

            sheet.addExcelRow(runRow_3);
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)20000);
        columnWidthMap.put((short)1, (short)5000);
        columnWidthMap.put((short)2, (short)5000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        columnWidthMap.put((short)5, (short)7000);
        columnWidthMap.put((short)6, (short)5000);
        columnWidthMap.put((short)7, (short)5000);
        columnWidthMap.put((short)8, (short)5000);
        columnWidthMap.put((short)9, (short)5000);
        columnWidthMap.put((short)10, (short)7000);
        columnWidthMap.put((short)11, (short)5000);
        columnWidthMap.put((short)12, (short)8000);
        columnWidthMap.put((short)13, (short)8000);
        columnWidthMap.put((short)14, (short)8000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}
