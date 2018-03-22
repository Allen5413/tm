package com.zs.service.bank.notify.impl;

import com.zs.dao.bank.notifylog.FindNotifyByOrderNoDAO;
import com.zs.dao.bank.paylog.FindPayLogByCodeDAO;
import com.zs.domain.bank.BankNotifyLog;
import com.zs.domain.bank.BankPayLog;
import com.zs.domain.finance.StudentExpensePay;
import com.zs.service.bank.notify.BankNotifyService;
import com.zs.service.finance.studentexpensepay.AddStudentExpensePayService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Allen on 2015/10/28.
 */
@Service("bankNotifyService")
public class BankNotifyServiceImpl implements BankNotifyService {

    @Resource
    private FindPayLogByCodeDAO findPayLogByCodeDAO;
    @Resource
    private AddStudentExpensePayService addStudentExpensePayService;
    @Resource
    private FindNotifyByOrderNoDAO findNotifyByOrderNoDAO;

    @Override
    @Transactional
    public synchronized String notify(Map<String,String> params, String method, String flag) throws Exception {
        BankNotifyLog bankNotifyLog = new BankNotifyLog();
        String result = "0";
        //验签成功
        if("true".equals(flag)) {
            if (null == params) {
                bankNotifyLog.setEvent(BankNotifyLog.EVENTT_NOT);
                findNotifyByOrderNoDAO.save(bankNotifyLog);
            } else {
                String event = params.get("event");
                String timestamp = params.get("timestamp");
                String appid = params.get("appid");
                String order_no = params.get("order_no");
                String order_amount = params.get("order_amount");
                String order_time = params.get("order_time");
                String pay_time = params.get("pay_time");
                String sno = params.get("sno");
                String mac = params.get("mac");

                //注意：后台通知和前台通知有可能同时到来，注意 [需要防止重复处理]
                //查询该订单号是否已经记录了日志
                BankNotifyLog isExistsBankNotifyLog = findNotifyByOrderNoDAO.find(order_no);
                if (null == isExistsBankNotifyLog) {
                    //支付成功通知
                    if ("NOTIFY_ACQUIRE_SUCCESS".equalsIgnoreCase(event)) {
                        result = "0";
                        bankNotifyLog.setEvent(BankNotifyLog.EVENTT_AUTH_SUCCESS);
                        //支付成功，修改支付日志状态
                        BankPayLog bankPayLog = findPayLogByCodeDAO.find(order_no);
                        if (null != bankPayLog) {
                            bankPayLog.setState(BankPayLog.STATE_SUCCESS);
                            bankPayLog.setType(BankPayLog.TYPE_NOTIFY);
                            findPayLogByCodeDAO.update(bankPayLog);
                        }
                        //记录学生缴费
                        this.addPay(bankPayLog);
                    }
                    //支付失败通知
                    if ("NOTIFY_ACQUIRE_FAIL".equalsIgnoreCase(event)) {
                        bankNotifyLog.setEvent(BankNotifyLog.EVENTT_FAIL);
                        //支付成功，修改支付日志状态
                        BankPayLog bankPayLog = findPayLogByCodeDAO.find(order_no);
                        if (null != bankPayLog) {
                            bankPayLog.setState(BankPayLog.STATE_FAIL);
                            bankPayLog.setType(BankPayLog.TYPE_NOTIFY);
                            findPayLogByCodeDAO.update(bankPayLog);
                        }
                    }
                    //退款成功通知
                    if ("NOTIFY_REFUND_SUCCESS".equalsIgnoreCase(event)) {
                        result = "0";
                        bankNotifyLog.setEvent(BankNotifyLog.EVENTT_RETURN_SUCCESS);
                    }
                    //快捷支付认证成功通知
                    if ("NOTIFY_AUTH_SUCCESS".equalsIgnoreCase(event)) {
                        result = "0";
                        bankNotifyLog.setEvent(BankNotifyLog.EVENTT_AUTH_SUCCESS);
                    }

                    bankNotifyLog.setAppid(appid);
                    bankNotifyLog.setMac(mac);
                    bankNotifyLog.setOrderAmount(Double.valueOf(order_amount));
                    bankNotifyLog.setOrderNo(order_no);
                    bankNotifyLog.setOrderTime(order_time);
                    bankNotifyLog.setPayTime(pay_time);
                    bankNotifyLog.setSno(sno);
                    bankNotifyLog.setTimestamp(timestamp);

                    findNotifyByOrderNoDAO.save(bankNotifyLog);
                }
            }
        }

        //验签失败
        else if("false".equals(flag)){
            result = "1";
            bankNotifyLog.setEvent(BankNotifyLog.NOTIFY_SIGNATURE_FAIL);
            findNotifyByOrderNoDAO.save(bankNotifyLog);
        }

        //操作异常
        else{
            result = "2";
            bankNotifyLog.setEvent(flag);
            findNotifyByOrderNoDAO.save(bankNotifyLog);
        }
        return result;
    }

    protected void addPay(BankPayLog bankPayLog)throws Exception{
        //学生缴费信息
        if(BankPayLog.PAY_USER_TYPE_STUDENT == bankPayLog.getPayUserType()){
            double money = bankPayLog.getMoney();
            StudentExpensePay studentExpensePay = new StudentExpensePay();
            studentExpensePay.setStudentCode(bankPayLog.getPayUserCode());
            studentExpensePay.setMoney(Float.parseFloat(money+""));
            studentExpensePay.setPayUserType(StudentExpensePay.PAYUSERTYPE_STUDENT);
            studentExpensePay.setPayType(StudentExpensePay.PAY_TYPE_ONLINE_BANK);
            studentExpensePay.setArrivalTime(DateTools.getLongNowTime());
            addStudentExpensePayService.addStudentExpensePay(studentExpensePay, bankPayLog.getOperator(), bankPayLog.getPayUserCode());
        }
    }
}
