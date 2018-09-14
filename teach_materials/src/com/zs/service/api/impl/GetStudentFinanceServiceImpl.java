package com.zs.service.api.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sale.onceorder.FindOnceOrderCountByStudentCodeAndSemesterIdDAO;
import com.zs.dao.sale.onceordertm.FindOnceTMPriceByStudentCodeForNotSendOnceOrderDAO;
import com.zs.dao.sale.studentbookordertm.FindTMPriceByStudentCodeForNotSendOnceOrderDAO;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.finance.StudentExpense;
import com.zs.domain.sync.Student;
import com.zs.service.api.GetStudentFinanceService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.finance.studentexpense.FindStuEPageByWhereService;
import com.zs.service.sync.spec.FindSpecCourseTMBySpecAndLevelService;
import com.zs.service.sync.student.FindStudentByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Allen on 2017/5/27.
 */
@Service("getStudentFinanceService")
public class GetStudentFinanceServiceImpl extends EntityServiceImpl<Student, StudentDAO> implements GetStudentFinanceService{

    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindStudentByCodeService findStudentByCodeService;
    @Resource
    private FindSpecCourseTMBySpecAndLevelService findSpecCourseTMBySpecAndLevelService;
    @Resource
    private FindOnceTMPriceByStudentCodeForNotSendOnceOrderDAO findOnceTMPriceByStudentCodeForNotSendOnceOrderDAO;
    @Resource
    private FindStuEPageByWhereService findStuEPageByWhereService;
    @Resource
    private FindTMPriceByStudentCodeForNotSendOnceOrderDAO findTMPriceByStudentCodeForNotSendOnceOrderDAO;
    @Resource
    private FindOnceOrderCountByStudentCodeAndSemesterIdDAO findOnceOrderCountByStudentCodeAndSemesterIdDAO;

    @Override
    public JSONObject get(String code) throws Exception {
        JSONObject json = new JSONObject();
        BigDecimal totalPriceBD = new BigDecimal(0);
        Semester semester = findNowSemesterService.getNowSemester();
        Student student = findStudentByCodeService.getStudentByCode(code);
        if(null == student || student.getState() != 0){
            throw new BusinessException("该学生不是在籍学生");
        }
        if(semester.getYear().toString().equals(student.getStudyEnterYear().toString()) && semester.getQuarter().toString().equals(student.getStudyQuarter().toString())){
            //新生的情况，直接通过专业层次获取对应课程，然后查询课程教材的费用
            totalPriceBD = this.countNewStudent(code, totalPriceBD, student.getSpecCode(), student.getLevelCode());
        }else{
            //获取是否有一次性订单，没有说明是新生才转过来成旧生，一次性订单还没同步，所以还是按新生规则计算
            totalPriceBD = this.countOldStudent(code, totalPriceBD, semester.getId(), student.getSpecCode(), student.getLevelCode());
        }
        json.put("price", null == totalPriceBD ? 0 : 0 > totalPriceBD.doubleValue() ? 0 : totalPriceBD);
        return json;
    }

    /**
     * 新生的总缴费计算方法
     * @param code
     * @param totalPriceBD
     * @param specCode
     * @param levelCode
     * @return
     */
    private BigDecimal countNewStudent(String code, BigDecimal totalPriceBD, String specCode, String levelCode)throws Exception{
        JSONObject resultJSON = findSpecCourseTMBySpecAndLevelService.find(specCode, levelCode);
        Set<String> s = resultJSON.keySet();
        for(String key : s){
            JSONArray jsonArray = (JSONArray) resultJSON.get(key);
            System.out.println(key+"   "+(null != jsonArray ? jsonArray.size() : 0));
            if(null != jsonArray && 0 < jsonArray.size()){
                for(int i=0; i<jsonArray.size(); i++){
                    JSONObject tmJSON = (JSONObject) jsonArray.get(i);
                    BigDecimal price = new BigDecimal(null == tmJSON.get("price") ? "0" : tmJSON.get("price").toString());
                    System.out.println(key+"   price: "+ price);
                    totalPriceBD = totalPriceBD.add(price);
                }
            }
        }
        System.out.println("totalPriceBD: "+ totalPriceBD);
        //计算下于50收取50，大于50进1收取
        String totalPriceStr = totalPriceBD.toString();
        if(3 > totalPriceStr.length()) {
            if (0 < Double.valueOf(totalPriceStr) && Double.valueOf(totalPriceStr) < 50) {
                totalPriceStr = "50";
            }
            if (50 < Double.valueOf(totalPriceStr) && Double.valueOf(totalPriceStr) < 100) {
                totalPriceStr = "100";
            }
        }else{
            String temp = "";
            double tempNum = 0;
            if(0 < totalPriceStr.indexOf(".")){
                temp = totalPriceStr.substring(0, totalPriceStr.indexOf(".")-2);
                tempNum = Double.parseDouble(totalPriceStr.substring(totalPriceStr.indexOf(".") - 2, totalPriceStr.length()));
            }else{
                temp = totalPriceStr.substring(0, totalPriceStr.length()-2);
                tempNum = Double.parseDouble(totalPriceStr.substring(totalPriceStr.length()-2, totalPriceStr.length()));
            }
            if(0 < tempNum && tempNum < 50){
                totalPriceStr = temp+"50";
            }
            if(50 < tempNum && tempNum < 100){
                totalPriceStr = (Integer.parseInt(temp) + 1)+"00";
            }
        }
        totalPriceBD = new BigDecimal(totalPriceStr);

        Map<String, String> params = new HashMap<String, String>();
        params.put("studentCode", code);
        PageInfo<StudentExpense> pageInfo = new PageInfo<StudentExpense>();
        pageInfo.setCurrentPage(1);
        pageInfo.setCountOfCurrentPage(99);
        net.sf.json.JSONObject returnJSON = findStuEPageByWhereService.findPageByWhere(pageInfo, params, null);
        BigDecimal pay = new BigDecimal(returnJSON.get("totalPayPrice").toString());
        totalPriceBD = totalPriceBD.subtract(pay);
        return totalPriceBD;
    }

    /**
     * 旧生的总缴费计算方法
     * @param code
     * @param totalPriceBD
     * @param specCode
     * @param levelCode
     * @return
     */
    private BigDecimal countOldStudent(String code, BigDecimal totalPriceBD, long semesterId, String specCode, String levelCode)throws Exception{
        BigInteger num = findOnceOrderCountByStudentCodeAndSemesterIdDAO.findOrderCount(code);
        if(null != num && 0 < num.intValue()){
            //获取一次性订单未发出的金额
            totalPriceBD = new BigDecimal(findOnceTMPriceByStudentCodeForNotSendOnceOrderDAO.find(code, semesterId));
            //获取订单未发出的金额
            totalPriceBD = totalPriceBD.add(new BigDecimal(findTMPriceByStudentCodeForNotSendOnceOrderDAO.find(code, semesterId)));
            Map<String, String> params = new HashMap<String, String>();
            params.put("studentCode", code);
            PageInfo<StudentExpense> pageInfo = new PageInfo<StudentExpense>();
            pageInfo.setCurrentPage(1);
            pageInfo.setCountOfCurrentPage(99);
            net.sf.json.JSONObject returnJSON = findStuEPageByWhereService.findPageByWhere(pageInfo, params, null);
            BigDecimal acc = new BigDecimal(returnJSON.get("totalAccPrice").toString());
            totalPriceBD = totalPriceBD.subtract(acc);
        }else{
            totalPriceBD = this.countNewStudent(code, totalPriceBD, specCode, levelCode);
        }
        return totalPriceBD.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
