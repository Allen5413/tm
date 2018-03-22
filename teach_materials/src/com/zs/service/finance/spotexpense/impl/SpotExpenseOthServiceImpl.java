package com.zs.service.finance.spotexpense.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zs.dao.sync.student.StudentDAO;
import com.zs.service.finance.spotexpensepay.AddSpotExpensePayService;
import com.zs.service.finance.studentexpensepay.AddStudentExpensePayService;
import com.zs.tools.DateTools;
import com.zs.tools.ExcelTools;
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

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpense.CenterPayDao;
import com.zs.dao.finance.studentexpense.SpotPayPolTempDao;
import com.zs.dao.finance.studentexpense.SpotTempPayDao;
import com.zs.domain.finance.SpotExpenseOth;
import com.zs.domain.finance.SpotExpensePay;
import com.zs.domain.finance.SpotPayPolTemp;
import com.zs.domain.finance.SpotPayTemp;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.spotexpense.SpotExpenseOthService;
import com.zs.tools.DateJsonValueProcessorTools;
import com.zs.tools.StringTools;

@Service("spotExpenseOthService")
public class SpotExpenseOthServiceImpl extends EntityServiceImpl implements SpotExpenseOthService{

    @Resource
    private CenterPayDao centerPayDao;
    @Resource
    private SpotTempPayDao spotTempPayDao;
    @Resource
    private SpotPayPolTempDao spotPayPolTempDao;
    @Resource
    private AddStudentExpensePayService addStudentExpensePayService;
    @Resource
    private AddSpotExpensePayService addSpotExpensePayService;
    @Resource
    private StudentDAO studentDAO;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo querySpotExpenseOth(PageInfo pageInfo,Map<String, String> map) {

        PageInfo pagInfo = centerPayDao.queryCenterPay(pageInfo, map,null);

        if(null != pagInfo){
            List<Object[]> datList = pagInfo.getPageResults();

            if(null != datList && datList.size() > 0){
                JSONArray jsonArray = new JSONArray();
                for(Object[] objArr : datList){
                    SpotExpenseOth spotExpenseOth = new SpotExpenseOth();
                    spotExpenseOth.setClearTime((Date)objArr[6]);
                    JsonConfig jsonConfig = new JsonConfig();
                    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                    JSONObject jsonObj = JSONObject.fromObject(spotExpenseOth, jsonConfig);
                    jsonObj.put("stuFie",objArr[0]);
                    jsonObj.put("proName", objArr[1]);
                    jsonObj.put("potCode", objArr[2]);
                    jsonObj.put("potName", objArr[3]);
                    jsonObj.put("doPayTotShow",StringTools.getFinancePrice(String.valueOf(objArr[5])));
                    jsonObj.put("buyTotShow", StringTools.getFinancePrice(String.valueOf(objArr[4])));
                    jsonObj.put("doPayTot",objArr[5]);
                    jsonObj.put("buyTot", objArr[4]);
                    jsonObj.put("stuAccShow", StringTools.getFinancePrice(String.valueOf(objArr[9])));
                    jsonObj.put("stuOwnShow", StringTools.getFinancePrice(String.valueOf(objArr[8])));
                    jsonObj.put("stuAcc", objArr[9]);
                    jsonObj.put("stuOwn",objArr[8]);
                    jsonObj.put("semesterId", objArr[10]);

                    jsonObj.put("clearTime",(null == jsonObj || "null".equals(jsonObj.get("clearTime").toString())) ? "" : jsonObj.get("clearTime"));
                    jsonObj.put("spotOwnId",objArr[7]);
                    Long ownId = Long.parseLong("" + objArr[7]);
                    List<SpotPayTemp> spotPayTemp = spotTempPayDao.querySpotPayTempByOwnId(ownId);
                    if(null != spotPayTemp && spotPayTemp.size() == 0){
                        jsonObj.put("showPay",1);
                    }else if(null != spotPayTemp && spotPayTemp.size() > 0){
                        List<SpotPayPolTemp> spotPayPolTempList  = spotPayPolTempDao.querySpotPayPolTempByOwnId(ownId);
                        if(null != spotPayPolTempList && spotPayPolTempList.size() > 0){
                            SpotPayPolTemp spotPayPolTemp = spotPayPolTempList.get(0);
                            if(spotPayPolTemp.getIsSure().equals("0")){
                                jsonObj.put("showSur", 0);
                            }else if(spotPayPolTemp.getIsSure().equals("1")){
                                jsonObj.put("showSur", 1);
                            }else if(spotPayPolTemp.getIsSure().equals("2")){
                                jsonObj.put("showSur", 2);
                            }
                        }
                    }
                    jsonArray.add(jsonObj);
                }
                List<Object[]> spotNumSum = this.centerPayDao.quryCenterPaySum(map);
                if(null != spotNumSum && spotNumSum.size() > 0){
                    for(Object[] datObjArr : spotNumSum){
                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("paySum", StringTools.getFinancePrice(String.valueOf((Double)datObjArr[0])));
                        jsonObj.put("buySum", StringTools.getFinancePrice(String.valueOf((Double)datObjArr[1])));
                        jsonObj.put("accSum", StringTools.getFinancePrice(String.valueOf((Double)datObjArr[3])));
                        jsonObj.put("ownSum", StringTools.getFinancePrice(String.valueOf((Double)datObjArr[2])));

                        jsonArray.add(jsonObj);
                    }

                }
                pagInfo.setPageResults(jsonArray);
            }
        }

        return pagInfo;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public JSONObject querySpotStuOwn(Map<String, String> paramsMap) {
        JSONObject jsonObject = new JSONObject();

        PageInfo pagInfo = new PageInfo();
        pagInfo.setCurrentPage(1);
        pagInfo.setCountOfCurrentPage(999999);

        pagInfo = this.centerPayDao.querySpotStuOwn(pagInfo, paramsMap);

        if(null != pagInfo){
            List<Object[]> datList = pagInfo.getPageResults();
            JSONArray jsonArray = new JSONArray();
            double totalPrice = 0;
            if(null != datList && datList.size() > 0){
                for(Object[] objArr : datList){
                    if((Double)objArr[3] > 0){
                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("toStuYear",objArr[0]);
                        jsonObj.put("stuCode", objArr[1]);
                        jsonObj.put("stuName", objArr[2]);
                        jsonObj.put("stuOwn", objArr[3]);
                        jsonArray.add(jsonObj);

                        totalPrice = new BigDecimal(totalPrice).add(new BigDecimal(objArr[3].toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }
                pagInfo.setPageResults(jsonArray);
            }
            jsonObject.put("data", jsonArray);
            jsonObject.put("totalPrice", totalPrice);
        }

        return jsonObject;
    }

    @Override
    @Transactional
    public String downSpotStuOwn(Map<String, String> map, String fileName) throws Exception {
        JSONObject resultJSON = this.querySpotStuOwn(map);
        JSONArray jsonArray = (JSONArray) resultJSON.get("data");
        double totalPrice = Double.parseDouble(resultJSON.get("totalPrice").toString());
        return this.createExcel(jsonArray, "", totalPrice, map.get("spotCode"), fileName);
    }


    private String createExcel(JSONArray data, String title, double totalPrice, String spotCode, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        if(null != data && data.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "入学年季",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "学号",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "姓名",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "欠款金额",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < data.size(); i++){
                JSONObject jsonObject = (JSONObject)data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_8 = new ExcelCell(index,(byte)0, jsonObject.get("toStuYear").toString(),false,"",9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)1, jsonObject.get("stuCode").toString(),false,"", 9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)2, jsonObject.get("stuName").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)3, jsonObject.get("stuOwn").toString(),false,"",9);

                runRow_2.addExcelCell(runCell_8);
                runRow_2.addExcelCell(runCell_9);
                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);

                sheet.addExcelRow(runRow_2);

                index ++;
            }

            ExcelRow runRow_3 = new ExcelRow(index);
            ExcelCell runCell_15 = new ExcelCell(index,(byte)2, "合计",false,"",9);
            ExcelCell runCell_16 = new ExcelCell(index,(byte)3, totalPrice+"",false,"", 9);


            runRow_3.addExcelCell(runCell_15);
            runRow_3.addExcelCell(runCell_16);


            sheet.addExcelRow(runRow_3);

            index ++;
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)7000);
        columnWidthMap.put((short)1, (short)11000);
        columnWidthMap.put((short)2, (short)7000);
        columnWidthMap.put((short)3, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void saveTempPay(String subStr,String spotOwnId,String userLoginName,String sumPay,String imagUrl,String spotCode,String payWay,String remark) {
        if(null != subStr && !"".equals(subStr)){

            SpotPayPolTemp spotPayPolTemp = new SpotPayPolTemp();
            spotPayPolTemp.setCreateTime(new Date());
            spotPayPolTemp.setCreator(userLoginName);
            spotPayPolTemp.setImagUrl(imagUrl);
            spotPayPolTemp.setOwnId(Long.parseLong(spotOwnId));
            spotPayPolTemp.setSpotCode(spotCode);
            spotPayPolTemp.setSpotMoney(Float.parseFloat(sumPay));
            spotPayPolTemp.setIsSure("0");
            spotPayPolTemp.setPayWay(payWay);
            spotPayPolTemp.setIsSpot("-1".equals(spotOwnId) ? SpotPayPolTemp.IS_SPOT_YES : SpotPayPolTemp.IS_SPOT_NOT);
            spotPayPolTemp.setRemark(remark);
            spotPayPolTempDao.save(spotPayPolTemp);

            String[] stuPayArr = subStr.split("%");
            if(null != stuPayArr && stuPayArr.length > 0){
                for(String stuPay : stuPayArr){
                    String[] payDetailArr = stuPay.split("\\$");
                    if(null != payDetailArr && payDetailArr.length > 0){
                        String stuCode = payDetailArr[0];
                        String stuMon = payDetailArr[2];

                        SpotPayTemp spotPayTemp = new SpotPayTemp();
                        spotPayTemp.setCreateTime(new Date());
                        spotPayTemp.setCreator(userLoginName);
                        spotPayTemp.setOwnId(Long.parseLong(spotOwnId));
                        spotPayTemp.setStuCode(stuCode);
                        spotPayTemp.setStuMoney(Float.parseFloat(stuMon));
                        spotPayTemp.setPolId(spotPayPolTemp.getId());

                        spotTempPayDao.save(spotPayTemp);
                    }
                }
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo querySpotPayPolTemp(PageInfo pageInfo,Map<String, String> paramsMap){

        PageInfo pagInfo = this.centerPayDao.querySpotPayPolTemp(pageInfo, paramsMap);

        if(null != pagInfo){
            List<Object[]> datList = pagInfo.getPageResults();

            if(null != datList && datList.size() > 0){
                JSONArray jsonArray = new JSONArray();
                for(Object[] objArr : datList){
                    SpotPayPolTemp spotPayPolTemp = new SpotPayPolTemp();
                    spotPayPolTemp.setCreateTime((Date)objArr[4]);
                    spotPayPolTemp.setVerifyTime((Date)objArr[6]);
                    JsonConfig jsonConfig = new JsonConfig();
                    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessorTools());
                    JSONObject jsonObj = JSONObject.fromObject(spotPayPolTemp, jsonConfig);
                    jsonObj.put("spotCode", objArr[0]);
                    jsonObj.put("spotName", objArr[1]);
                    jsonObj.put("spotMoney", StringTools.getFinancePrice(String.valueOf(objArr[2])));
                    jsonObj.put("payer", objArr[3]);
                    jsonObj.put("payTime", (null == jsonObj || "null".equals(jsonObj.get("createTime").toString())) ? "" : jsonObj.get("createTime"));
                    jsonObj.put("verifyer", objArr[5]);
                    jsonObj.put("verifyTime", (null == jsonObj.get("verifyTime") || "null".equals(jsonObj.get("verifyTime").toString())) ? "" : jsonObj.get("verifyTime"));
                    if(((String)objArr[7]).equals("0")){
                        jsonObj.put("status","未审核");
                    }else if(((String)objArr[7]).equals("1")){
                        jsonObj.put("status","审核通过");
                    }else if(((String)objArr[7]).equals("2")){
                        jsonObj.put("status","审核未通过");
                    }
                    jsonObj.put("polId",objArr[8]);
                    jsonObj.put("payWay",objArr[9]);
                    jsonObj.put("isSpot",objArr[10]);
                    jsonObj.put("remark",objArr[11]);
                    jsonArray.add(jsonObj);
                }
                pagInfo.setPageResults(jsonArray);
            }
        }
        return pagInfo;
    }

    @Override
    public List<SpotPayTemp> querySpotPayTempByPolId(Long polId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public PageInfo querySpotTemp(Map<String, String> paramsMap) {

        PageInfo pagInfo = new PageInfo();
        pagInfo = this.centerPayDao.querySpotTemp(pagInfo, paramsMap);

        if(null != pagInfo){
            List<Object[]> datList = pagInfo.getPageResults();

            if(null != datList && datList.size() > 0){
                JSONArray jsonArray = new JSONArray();
                for(Object[] objArr : datList){
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("stuCode", objArr[0]);
                    jSONObject.put("stuName", objArr[1]);
                    jSONObject.put("studMoney", objArr[2]);

                    jsonArray.add(jSONObject);
                }
                pagInfo.setPageResults(jsonArray);
            }
        }
        return pagInfo;
    }

    @Override
    @Transactional
    public String downSpotTemp(Map<String, String> map, String fileName) throws Exception {
        PageInfo pageInfo = this.querySpotTemp(map);
        List list = pageInfo.getPageResults();

        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        BigDecimal totalPrice = new BigDecimal(0);
        if(null != list && list.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "学号",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "姓名",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "金额",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < list.size(); i++){
                JSONObject jsonObject = (JSONObject)list.get(i);
                BigDecimal money = new BigDecimal(null == jsonObject.get("studMoney") ? "0" : jsonObject.get("studMoney").toString());
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_4 = new ExcelCell(index,(byte)0, jsonObject.get("stuCode").toString(),false,"",9);
                ExcelCell runCell_5 = new ExcelCell(index,(byte)1, jsonObject.get("stuName").toString(),false,"",9);
                ExcelCell runCell_6 = new ExcelCell(index,(byte)2, money.toString(),false,"", 9);

                runRow_2.addExcelCell(runCell_4);
                runRow_2.addExcelCell(runCell_5);
                runRow_2.addExcelCell(runCell_6);
                sheet.addExcelRow(runRow_2);
                index ++;

                totalPrice = totalPrice.add(money).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            ExcelRow runRow3 = new ExcelRow(index);
            ExcelCell runCell_7 = new ExcelCell(index,(byte)1, "合计",false,"",30);
            ExcelCell runCell_8 = new ExcelCell(index,(byte)2, totalPrice.toString(),false,"",30);
            runRow3.addExcelCell(runCell_7);
            runRow3.addExcelCell(runCell_8);
            sheet.addExcelRow(runRow3);
            index ++;
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)7000);
        columnWidthMap.put((short)1, (short)11000);
        columnWidthMap.put((short)2, (short)7000);
        columnWidthMap.put((short)3, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void verifySure(Long spotPolId,String userLoginName,String loginName, String arrivalTime, String payType, String remark) throws Exception{
        try {
            SpotPayPolTemp spotPayPolTemp = this.spotPayPolTempDao.get(spotPolId);
            //如果是审核交学生的钱
            if(spotPayPolTemp.getIsSpot() == SpotPayPolTemp.IS_SPOT_NOT) {
                Map<String, String> paramsMap = new HashMap<String, String>();
                paramsMap.put("polId", String.valueOf(spotPolId));
                PageInfo spotPayTemp = this.centerPayDao.querySpotTemp(new PageInfo(), paramsMap);
                //向学生支付表中写入数据
                if (null != spotPayTemp && spotPayTemp.getPageResults().size() > 0) {
                    List<Object[]> datList = spotPayTemp.getPageResults();
                    for (Object[] bea : datList) {

                        String stuCode = (String) bea[0];
                        Float stuMon = (Float) bea[2];

                        StudentExpensePay studentExpensePay = new StudentExpensePay();
                        studentExpensePay.setMoney(stuMon);
                        studentExpensePay.setStudentCode(stuCode);
                        studentExpensePay.setPayUserType(StudentExpensePay.PAYUSERTYPE_SPOT);
                        studentExpensePay.setArrivalTime(DateTools.getFormatDate(arrivalTime, "yyyy-MM-dd HH:mm:ss"));
                        studentExpensePay.setPayType(Integer.parseInt(payType));
                        studentExpensePay.setRemark(remark);
                        addStudentExpensePayService.addStudentExpensePay(studentExpensePay, userLoginName, loginName);
                    }
                }
            }
            //如果是审核交中心的钱
            if(spotPayPolTemp.getIsSpot() == SpotPayPolTemp.IS_SPOT_YES) {
                //向中心支付表中写入数据
                SpotExpensePay spotExpensePay = new SpotExpensePay();
                spotExpensePay.setSpotCode(spotPayPolTemp.getSpotCode());
                spotExpensePay.setMoney(spotPayPolTemp.getSpotMoney());
                spotExpensePay.setArrivalTime(DateTools.getFormatDate(arrivalTime, "yyyy-MM-dd HH:mm:ss"));
                spotExpensePay.setPayType(Integer.parseInt(payType));
                spotExpensePay.setRemark(remark);
                addSpotExpensePayService.addSpotEP(spotExpensePay, userLoginName);
            }

            //更新审核状态
            spotPayPolTemp.setIsSure(SpotPayPolTemp.IS_SURE_PASS);
            spotPayPolTemp.setVerifyTime(new Date());
            spotPayPolTemp.setVerifyer(userLoginName);
            this.spotPayPolTempDao.update(spotPayPolTemp);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void verifyNotSure(Long spotPolId,String userLoginName) throws Exception{
        try {
            SpotPayPolTemp spotPayPolTemp = this.spotPayPolTempDao.get(spotPolId);
            //更新审核状态
            spotPayPolTemp.setIsSure(SpotPayPolTemp.IS_SURE_NOT);
            spotPayPolTemp.setVerifyTime(new Date());
            spotPayPolTemp.setVerifyer(userLoginName);
            this.spotPayPolTempDao.update(spotPayPolTemp);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String downStudentOrderTm(String codeMoneys, String fileName) throws Exception {
        if(StringUtils.isEmpty(codeMoneys)){
            throw new Exception("没有传入学生信息");
        }
        String[] codeMoneyArray = codeMoneys.split(",");
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        int num = 2017050001;
        for(int i = 0; i < codeMoneyArray.length; i++){
            String codeMoney = codeMoneyArray[i];
            String code = codeMoney.split("_")[0];
            double money = Double.parseDouble(codeMoney.split("_")[1]);
            //查询学生的购书明细
            List<Object[]> list = studentDAO.findOrderTmByStudentCode(code);
            if(null != list && 0 < list.size()){
                double tempMoney = 0;
                double nowPrice = 0;
                for(int j = 0; j < list.size(); j++){
                    Object[] objs = list.get(j);
                    String studentName = objs[0].toString();
                    String tmName = objs[1].toString();
                    String count = objs[2].toString();
                    String price = objs[3].toString();
                    String totalPrice = objs[4].toString();

                    ExcelRow runRow = new ExcelRow(index);
                    ExcelCell runCell_1 = new ExcelCell(index,(byte)0, num+"",false,"",9);
                    ExcelCell runCell_2 = new ExcelCell(index,(byte)1, studentName,false,"",9);
                    ExcelCell runCell_3 = new ExcelCell(index,(byte)2, tmName,false,"", 9);
                    ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "本",false,"", 9);
                    ExcelCell runCell_5 = null;
                    ExcelCell runCell_6 = null;
                    ExcelCell runCell_7 = null;
                    ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "免税",false,"", 9);
                    ExcelCell runCell_9 = new ExcelCell(index,(byte)8, "",false,"", 9);
                    ExcelCell runCell_10 = new ExcelCell(index,(byte)9, "",false,"", 9);
                    ExcelCell runCell_11 = new ExcelCell(index,(byte)10, "",false,"", 9);

                    nowPrice = Double.parseDouble(totalPrice);
                    if(j+1 <= list.size()-1){
                        Object[] objs2 = list.get(j+1);
                        String totalPrice2 = objs2[4].toString();
                        if(money <= tempMoney + Double.parseDouble(totalPrice) + Double.parseDouble(totalPrice2)){
                            if(money - tempMoney - Double.parseDouble(totalPrice) < 10){
                                nowPrice = Double.parseDouble(totalPrice) - (money - tempMoney - Double.parseDouble(totalPrice));
                            }
                        }
                    }

                    if(money <= tempMoney + nowPrice){
                        runCell_5 = new ExcelCell(index,(byte)4, count,false,"", 9);
                        runCell_6 = new ExcelCell(index,(byte)5, new BigDecimal(money-tempMoney).divide(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),false,"", 9);
                        runCell_7 = new ExcelCell(index,(byte)6, new BigDecimal(money-tempMoney).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),false,"", 9);
                    }else{
                        runCell_5 = new ExcelCell(index,(byte)4, count,false,"", 9);
                        runCell_6 = new ExcelCell(index,(byte)5, new BigDecimal(nowPrice).divide(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),false,"", 9);
                        runCell_7 = new ExcelCell(index,(byte)6, new BigDecimal(nowPrice).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),false,"", 9);
                    }
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
                    sheet.addExcelRow(runRow);
                    index ++;
                    if(money < tempMoney + nowPrice){
                        break;
                    }
                    tempMoney += nowPrice;
                }
            }
            num++;
        }

        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)5000);
        columnWidthMap.put((short)1, (short)5000);
        columnWidthMap.put((short)2, (short)12000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        columnWidthMap.put((short)5, (short)5000);
        columnWidthMap.put((short)6, (short)5000);
        columnWidthMap.put((short)7, (short)5000);
        columnWidthMap.put((short)8, (short)5000);
        columnWidthMap.put((short)9, (short)5000);
        columnWidthMap.put((short)10, (short)5000);

        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }

    private class StuTempDat{

        private Long semeter_id;

        private Float sumPaySemeter;

        private Float stuAccSemeter;

        private Float stuOwnSemeter;

        public Long getSemeter_id() {
            return semeter_id;
        }

        public void setSemeter_id(Long semeter_id) {
            this.semeter_id = semeter_id;
        }

        public Float getSumPaySemeter() {
            return sumPaySemeter;
        }

        public void setSumPaySemeter(Float sumPaySemeter) {
            this.sumPaySemeter = sumPaySemeter;
        }

        public Float getStuAccSemeter() {
            return stuAccSemeter;
        }

        public void setStuAccSemeter(Float stuAccSemeter) {
            this.stuAccSemeter = stuAccSemeter;
        }

        public Float getStuOwnSemeter() {
            return stuOwnSemeter;
        }

        public void setStuOwnSemeter(Float stuOwnSemeter) {
            this.stuOwnSemeter = stuOwnSemeter;
        }

    }

}
