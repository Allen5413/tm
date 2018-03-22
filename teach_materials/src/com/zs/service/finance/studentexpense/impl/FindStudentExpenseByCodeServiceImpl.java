package com.zs.service.finance.studentexpense.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpense.StudentExpenseDao;
import com.zs.domain.finance.StudentExpense;
import com.zs.service.finance.studentexpense.FindStudentExpenseByCodeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Allen on 2016/5/24.
 */
@Service("findStudentExpenseByCodeService")
public class FindStudentExpenseByCodeServiceImpl extends EntityServiceImpl<StudentExpense, StudentExpenseDao> implements FindStudentExpenseByCodeService {

    @Resource
    private StudentExpenseDao studentExpenseDao;

    @Override
    public JSONObject find(String code) {
        JSONObject jsonObject = new JSONObject();
        List<StudentExpense> studentExpenseList = studentExpenseDao.queryStudentExpenseOnSemeter(code);
        if(null != studentExpenseList){
            BigDecimal sumPay = new BigDecimal(0);
            BigDecimal sumBuy = new BigDecimal(0);
            BigDecimal sumAcc = new BigDecimal(0);
            BigDecimal sumOwn = new BigDecimal(0);
            for(StudentExpense studentExpense : studentExpenseList){
                sumPay = sumPay.add(new BigDecimal(studentExpense.getPay())).setScale(2, BigDecimal.ROUND_HALF_UP);
                sumBuy = sumBuy.add(new BigDecimal(studentExpense.getBuy())).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            BigDecimal temp = sumPay.subtract(sumBuy).setScale(2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
            if(temp.doubleValue() >= 0){
                sumAcc = temp;
            }else{
                sumOwn = sumBuy.subtract(sumPay).setScale(2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            jsonObject.put("sumPay", sumPay);
            jsonObject.put("sumBuy", sumBuy);
            jsonObject.put("sumAcc", sumAcc);
            jsonObject.put("sumOwn", sumOwn);
        }
        return jsonObject;
    }
}