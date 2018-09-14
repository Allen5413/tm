package com.zs.service.finance.studentexpensepay.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.studentexpense.FindTotalPayBuyByStudentCodeDAO;
import com.zs.dao.finance.studentexpense.StudentExpenseDao;
import com.zs.dao.finance.studentexpensepay.StudentExpensePayDao;
import com.zs.dao.sale.onceorder.EditOnceOrderForEnterNotSendByStudentCodeDAO;
import com.zs.dao.sale.studentbookorder.EditStudentBookOrderForEnterNotSendByStudentCodeDAO;
import com.zs.dao.sync.student.EditStudentIsForeverSendTmByCodeDAO;
import com.zs.domain.finance.StudentExpense;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.domain.sync.Student;
import com.zs.service.finance.studentexpensepay.RefundWxStudentExpensePayService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Allen on 2016/6/27.
 */
@Service("refundWxStudentExpensePayService")
public class RefundWxStudentExpensePayServiceImpl extends EntityServiceImpl<StudentExpensePay, StudentExpensePayDao>
                implements RefundWxStudentExpensePayService {

    @Resource
    private FindTotalPayBuyByStudentCodeDAO findTotalPayBuyByStudentCodeDAO;
    @Resource
    private StudentExpenseDao studentExpenseDao;
    @Resource
    private EditStudentBookOrderForEnterNotSendByStudentCodeDAO editStudentBookOrderForEnterNotSendByStudentCodeDAO;
    @Resource
    private EditOnceOrderForEnterNotSendByStudentCodeDAO editOnceOrderForEnterNotSendByStudentCodeDAO;
    @Resource
    private EditStudentIsForeverSendTmByCodeDAO editStudentIsForeverSendTmByCodeDAO;

    @Override
    @Transactional
    public void refund(String code, double money, String loginName) throws Exception {
        StudentExpensePay newStudentExpensePay = new StudentExpensePay();
        newStudentExpensePay.setStudentCode(code);
        newStudentExpensePay.setParentId(-1l);
        newStudentExpensePay.setCreator(loginName);
        newStudentExpensePay.setPayType(StudentExpensePay.PAY_TYPE_WXREFUND);
        newStudentExpensePay.setMoney(new BigDecimal(0).subtract(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
        newStudentExpensePay.setPayUserType(StudentExpensePay.PAYUSERTYPE_ADMIN);
        super.save(newStudentExpensePay);

        //查询学生总的交费信息
        Object[] objs = findTotalPayBuyByStudentCodeDAO.find(code);
        double totalPay = Double.parseDouble(objs[0]+"");

        //总交费要减去这次的退款费用
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

        //把当前该学生还未发出的订单，改为未确认；学生是否永久发书标识改为：否
        editStudentBookOrderForEnterNotSendByStudentCodeDAO.edit(code);
        editOnceOrderForEnterNotSendByStudentCodeDAO.edit(code);
        editStudentIsForeverSendTmByCodeDAO.edit(Student.IS_FOREVER_SNEDTM_NOT, code);
    }
}
