package com.zs.service.finance.studentexpensepay.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpense.FindTotalPayBuyByStudentCodeDAO;
import com.zs.dao.finance.studentexpense.StudentExpenseDao;
import com.zs.dao.finance.studentexpensepay.StudentExpensePayDao;
import com.zs.domain.finance.StudentExpense;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.finance.studentexpensepay.RedCStudentExpensePayService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Allen on 2016/6/27.
 */
@Service("redCStudentExpensePayService")
public class RedCStudentExpensePayServiceImpl extends EntityServiceImpl<StudentExpensePay, StudentExpensePayDao>
                implements RedCStudentExpensePayService{

    @Resource
    private FindTotalPayBuyByStudentCodeDAO findTotalPayBuyByStudentCodeDAO;
    @Resource
    private StudentExpenseDao studentExpenseDao;

    @Override
    @Transactional
    public void redC(long id, String remark, String money, String loginName) throws Exception {
        if(StringUtils.isEmpty(remark)){
            throw new BusinessException("请填写红冲备注");
        }
        StudentExpensePay studentExpensePay = super.get(id);
        if(null == studentExpensePay){
            throw new BusinessException("没有找到缴费信息");
        }
        studentExpensePay.setParentId(0l);
        super.update(studentExpensePay);

        StudentExpensePay newStudentExpensePay = new StudentExpensePay();
        newStudentExpensePay.setStudentCode(studentExpensePay.getStudentCode());
        newStudentExpensePay.setParentId(id);
        newStudentExpensePay.setCreator(loginName);
        newStudentExpensePay.setRemark(remark);
        newStudentExpensePay.setPayType(StudentExpensePay.PAY_TYPE_RED);
        newStudentExpensePay.setMoney(new BigDecimal(0).subtract(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
        newStudentExpensePay.setPayUserType(StudentExpensePay.PAYUSERTYPE_ADMIN);
        super.save(newStudentExpensePay);

        //查询学生总的交费信息
        Object[] objs = findTotalPayBuyByStudentCodeDAO.find(studentExpensePay.getStudentCode());
        double totalPay = Double.parseDouble(objs[0]+"");

        //总交费要减去这次的红冲费用
        totalPay = new BigDecimal(totalPay).subtract(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //重新计算学生财务信息
        List<StudentExpense> studentExpenseList = studentExpenseDao.queryStudentExpenseOnSemeter(studentExpensePay.getStudentCode());
        if(null != studentExpenseList && 0 < studentExpenseList.size()){
            for(int i=0; i < studentExpenseList.size(); i++){
                StudentExpense studentExpense = studentExpenseList.get(i);
                double buy = null == studentExpense.getBuy() ? 0 : studentExpense.getBuy();
                if (totalPay >= buy) {
                    //如果是最后一个学期，就把剩的钱全部录进去
                    if (i == studentExpenseList.size() - 1) {
                        studentExpense.setPay(new BigDecimal(totalPay).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                    } else {
                        studentExpense.setPay(new BigDecimal(buy).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                    }
                    if (null == studentExpense.getState() || studentExpense.getState() == StudentExpense.STATE_NO) {
                        studentExpense.setState(StudentExpense.STATE_YES);
                    }
                    if (studentExpense.getClearTime() == null) {
                        studentExpense.setClearTime(DateTools.getLongNowTime());
                    }
                    totalPay = new BigDecimal(totalPay).subtract(new BigDecimal(buy)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                } else {
                    studentExpense.setPay(new BigDecimal(totalPay).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                    studentExpense.setState(StudentExpense.STATE_NO);
                    studentExpense.setClearTime(null);
                    totalPay = 0;
                }
                findTotalPayBuyByStudentCodeDAO.update(studentExpense);
            }
        }
    }
}
