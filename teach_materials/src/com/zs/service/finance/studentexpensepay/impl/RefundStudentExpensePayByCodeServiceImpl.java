package com.zs.service.finance.studentexpensepay.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpense.FindTotalPayBuyByStudentCodeDAO;
import com.zs.dao.finance.studentexpense.StudentExpenseDao;
import com.zs.dao.finance.studentexpensepay.StudentExpensePayDao;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.finance.StudentExpense;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.domain.sync.Student;
import com.zs.service.finance.spotexpenseoth.SetSpotExpenseOthBySpotCodeService;
import com.zs.service.finance.studentexpensepay.RefundStudentExpensePayByCodeService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Formatter;
import java.util.List;

/**
 * 退款也是做insert操作，缴费不能删除，退款用负数
 * Created by Allen on 2016/1/11.
 */
@Service("refundStudentExpensePayByCodeService")
public class RefundStudentExpensePayByCodeServiceImpl extends EntityServiceImpl<StudentExpensePay, StudentExpensePayDao> implements RefundStudentExpensePayByCodeService {

    @Resource
    private StudentExpenseDao studentExpenseDao;
    @Resource
    private FindTotalPayBuyByStudentCodeDAO findTotalPayBuyByStudentCodeDAO;
    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;

    @Override
    @Transactional
    public void refund(String code, double money, String userName) throws Exception {
        //查询学生信息
        Student student = findStudentByCodeDAO.getStudentByCode(code);
        if(null == student){
            throw new BusinessException("没有找到学号"+code+"信息！");
        }

        //添加缴费记录
        StudentExpensePay studentExpensePay = new StudentExpensePay();
        studentExpensePay.setStudentCode(code);
        studentExpensePay.setMoney(new BigDecimal(0).subtract(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
        studentExpensePay.setPayUserType(StudentExpensePay.PAYUSERTYPE_SPOT);
        studentExpensePay.setPayType(StudentExpensePay.PAY_TYPE_TRANSFER);
        studentExpensePay.setCreator(userName);
        studentExpensePay.setRemark("该笔交费信息由退款操作来");
        super.save(studentExpensePay);

        //查询学生总的交费信息
        Object[] objs = findTotalPayBuyByStudentCodeDAO.find(code);
        double totalPay = Double.parseDouble(objs[0]+"");
        double totalBuy = Double.parseDouble(objs[1]+"");

        double temp = new BigDecimal(totalPay).subtract(new BigDecimal(totalBuy)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(money > temp){
            throw new BusinessException("学号："+code+", 退款金额大于了余额！");
        }

        //总交费要减去这次的扣费
        totalPay = new BigDecimal(totalPay).subtract(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //重新计算学生财务信息
        List<StudentExpense> studentExpenseList = studentExpenseDao.queryStudentExpenseOnSemeter(code);
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
