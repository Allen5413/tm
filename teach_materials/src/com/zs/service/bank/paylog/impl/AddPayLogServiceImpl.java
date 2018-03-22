package com.zs.service.bank.paylog.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.bank.paylog.FindPayLogForMaxCodeDAO;
import com.zs.dao.sync.student.FindStudentByCodeDAO;
import com.zs.domain.bank.BankPayLog;
import com.zs.domain.sync.Student;
import com.zs.epaysdk.EPay;
import com.zs.service.bank.paylog.AddPayLogService;
import com.zs.tools.UserTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Created by Allen on 2015/10/27.
 */
@Service("addPayLogService")
public class AddPayLogServiceImpl extends EntityServiceImpl<BankPayLog, FindPayLogForMaxCodeDAO> implements AddPayLogService {

    @Resource
    private FindStudentByCodeDAO findStudentByCodeDAO;
    @Resource
    private FindPayLogForMaxCodeDAO findPayLogForMaxCodeDAO;

    @Override
    @Transactional
    public synchronized String add(String money, String payUserCode, int payUserType, HttpServletRequest request) throws Exception {
        String loginName = UserTools.getLoginUserForName(request);
        if(BankPayLog.PAY_USER_TYPE_STUDENT == payUserType) {
            Student student = findStudentByCodeDAO.getStudentByCode(payUserCode);
            if (null == student) {
                throw new BusinessException("学号：" + loginName + ", 没有找到该学生信息");
            }
        }
        BankPayLog bankPayLog = new BankPayLog();
        bankPayLog.setCode(this.createCode());
        bankPayLog.setMoney(Float.parseFloat(money));
        bankPayLog.setPayUserCode(payUserCode);
        bankPayLog.setPayUserType(payUserType);

        String html = EPay.gpPay(bankPayLog.getCode(), money, "购买教材", "学生购买教材");
        bankPayLog.setPayForm(html);
        bankPayLog.setState(BankPayLog.STATE_WAIT);
        bankPayLog.setOperator(loginName);
        super.save(bankPayLog);
        return html;
    }

    protected String createCode(){
        StringBuilder code = new StringBuilder("");
        //得到当前日期
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        String month = (calendar.get(Calendar.MONTH)+1) < 10 ? "0"+(calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)+"";
        String day = calendar.get(Calendar.DATE) < 10 ? "0"+calendar.get(Calendar.DATE) : calendar.get(Calendar.DATE)+"";
        String hour = calendar.get(Calendar.HOUR_OF_DAY) < 10 ? "0"+calendar.get(Calendar.HOUR_OF_DAY) : calendar.get(Calendar.HOUR_OF_DAY)+"";
        String min = calendar.get(Calendar.MINUTE) < 10 ? "0"+calendar.get(Calendar.MINUTE) : calendar.get(Calendar.MINUTE)+"";
        String second = calendar.get(Calendar.SECOND) < 10 ? "0"+calendar.get(Calendar.SECOND) : calendar.get(Calendar.SECOND)+"";
        code.append(year);
        code.append(month);
        code.append(day);
        code.append(hour);
        code.append(min);
        code.append(second);
        //查询最大的顺序号
        BankPayLog bankPayLog = findPayLogForMaxCodeDAO.find();
        if(null == bankPayLog){
            code.append(String.format("%03d", 1));
        }else{
            String maxCodeNum = bankPayLog.getCode();
            int num = Integer.parseInt(maxCodeNum.substring(maxCodeNum.length()-3, maxCodeNum.length()));
            code.append(String.format("%03d", num+1));
        }
        return code.toString();
    }
}
