package com.zs.service.finance.refund.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.refund.FindRefundByStudentCodeForNotAuditDAO;
import com.zs.dao.finance.refund.FindRefundForMaxCodeDAO;
import com.zs.dao.finance.refund.RefundDAO;
import com.zs.dao.finance.refundlog.RefundLogDAO;
import com.zs.dao.finance.refundstudent.RefundStudentDAO;
import com.zs.domain.finance.Refund;
import com.zs.domain.finance.RefundLog;
import com.zs.domain.finance.RefundStudent;
import com.zs.service.finance.invoice.FindTotalPayAndTotalInvoiceBySpotCodeService;
import com.zs.service.finance.refund.AddRefundService;
import com.zs.tools.OrderCodeTools;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Allen on 2016/1/5 0005.
 */
@Service("addRefundService")
public class AddRefundServiceImpl extends EntityServiceImpl<Refund,RefundDAO> implements AddRefundService{

    @Resource
    private FindRefundForMaxCodeDAO findRefundForMaxCodeDAO;
    @Resource
    private RefundStudentDAO refundStudentDAO;
    @Resource
    private RefundLogDAO refundLogDAO;
    @Resource
    private FindRefundByStudentCodeForNotAuditDAO findRefundByStudentCodeForNotAuditDAO;
    @Resource
    private FindTotalPayAndTotalInvoiceBySpotCodeService findTotalPayAndTotalInvoiceBySpotCodeService;

    @Override
    @Transactional
    public void add(String studentMoneyDetails, String bankName, String bankCode, String spotCode, String company, String userName) throws Exception {
        if(StringUtils.isEmpty(studentMoneyDetails)){
            throw new BusinessException("没有传入退款学生信息！");
        }
        if(StringUtils.isEmpty(bankName)){
            throw new BusinessException("没有传入银行名称！");
        }
        if(StringUtils.isEmpty(bankCode)){
            throw new BusinessException("没有传入银行卡号信息！");
        }

        String[] studentMoneyDetailsArray = studentMoneyDetails.split("\\|");
        double moneyTotal = 0;
        for(String studentMoneyDetail : studentMoneyDetailsArray){
            String[] studentMoneyDetailArray = studentMoneyDetail.split("_");
            if(null != studentMoneyDetailArray && 1 < studentMoneyDetailArray.length){
                String money = studentMoneyDetailArray[1];
                moneyTotal = new BigDecimal(moneyTotal).add(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        //查询该学习中心总共交了好多钱, 查询该学习中心已经开过了好多钱的发票
        JSONObject json = findTotalPayAndTotalInvoiceBySpotCodeService.find(spotCode);
        double temp = new BigDecimal(json.get("payTotal").toString()).subtract(new BigDecimal(json.get("invoiceTotal").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(moneyTotal > temp){
            throw new BusinessException("该中心已经开了"+json.get("invoiceTotal").toString()+"元的发票，退款金额超额");
        }

        //查询当前日期最大的退款code
        String code = "";
        Refund refund = findRefundForMaxCodeDAO.findRefundForMaxCode();
        if(refund == null){
            code = OrderCodeTools.createRefundCode(spotCode, 1);
        }else{
            String maxCode = refund.getCode();
            code = OrderCodeTools.createRefundCode(spotCode, Integer.parseInt(maxCode.substring(maxCode.length()-2, maxCode.length())) + 1);
        }

        //添加退款申请
        Refund addRefund = new Refund();
        addRefund.setSpotCode(spotCode);
        addRefund.setBankName(bankName);
        addRefund.setBankCode(bankCode);
        addRefund.setCompany(company);
        addRefund.setCreator(userName);
        addRefund.setOperator(userName);
        addRefund.setState(Refund.STATE_WAIT_PRINT);
        addRefund.setCode(code);
        super.save(addRefund);

        //添加退款学生明细
        for(String studentMoneyDetail : studentMoneyDetailsArray){
            String[] studentMoneyDetailArray = studentMoneyDetail.split("_");
            if(null != studentMoneyDetailArray && 1 < studentMoneyDetailArray.length){
                String studentCode = studentMoneyDetailArray[0];
                String money = studentMoneyDetailArray[1];
                String detail = "";
                if(2 < studentMoneyDetailArray.length){
                    detail = studentMoneyDetailArray[2];
                }

                //查询该学生是否有未被退款得申请记录，如果有，就不能申请退款
                List<Refund> refundList = findRefundByStudentCodeForNotAuditDAO.find(studentCode);
                if(null != refundList && 0 < refundList.size()){
                    throw new BusinessException("学号："+studentCode+", 还有未被审核的退款申请，不能再次提交退款申请");
                }

                RefundStudent refundStudent = new RefundStudent();
                refundStudent.setCode(addRefund.getCode());
                refundStudent.setRefundDetail(detail);
                refundStudent.setMoney(Double.valueOf(money));
                refundStudent.setState(RefundStudent.STATE_WAIT);
                refundStudent.setStudentCode(studentCode);
                refundStudent.setCreator(userName);
                refundStudent.setOperator(userName);

                refundStudentDAO.save(refundStudent);
            }
        }

        //记录退款流程状态变更日志
        RefundLog refundLog = new RefundLog();
        refundLog.setCode(addRefund.getCode());
        refundLog.setState(Refund.STATE_WAIT_PRINT);
        refundLog.setOperator(userName);
        refundLogDAO.save(refundLog);
    }
}
