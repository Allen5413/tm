package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.invoice.FindInvoiceForStateIsWaitBySpotCodeDAO;
import com.zs.dao.finance.invoice.InvoiceDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.AddInvoiceService;
import com.zs.service.finance.invoice.FindTotalPayAndTotalInvoiceBySpotCodeService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Allen on 2016/5/4 0004.
 */
@Service("addInvoiceService")
public class AddInvoiceServiceImpl extends EntityServiceImpl<Invoice, InvoiceDAO> implements AddInvoiceService {

    @Resource
    private FindTotalPayAndTotalInvoiceBySpotCodeService findTotalPayAndTotalInvoiceBySpotCodeService;
    @Resource
    private FindInvoiceForStateIsWaitBySpotCodeDAO findInvoiceForStateIsWaitBySpotCodeDAO;

    @Override
    @Transactional
    public void add(String codeMoneys, String spotCode, String longName) throws Exception {
        if(StringUtils.isEmpty(codeMoneys)){
            throw new BusinessException("没有传入数据");
        }
        String[] codeMoneyArray = codeMoneys.split(",");
        if(null != codeMoneyArray){
            BigDecimal moneyTotal = new BigDecimal(0);
            for(String codeMoney : codeMoneyArray){
                String[] code_Money = codeMoney.split("_");
                double money = Double.parseDouble(code_Money[1]);
                moneyTotal = moneyTotal.add(new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            //查询该学习中心总共交了好多钱, 查询该学习中心已经开过了好多钱的发票
            JSONObject json = findTotalPayAndTotalInvoiceBySpotCodeService.find(spotCode);
            double temp = new BigDecimal(json.get("payTotal").toString()).subtract(new BigDecimal(json.get("invoiceTotal").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if(moneyTotal.doubleValue() > temp){
                throw new BusinessException("您开票的金额超额了！");
            }

            for(String codeMoney : codeMoneyArray){
                String[] code_Money = codeMoney.split("_");
                String studentCode = code_Money[0];
                double money = Double.parseDouble(code_Money[1]);

                Invoice invoice = new Invoice();
                invoice.setStudentCode(studentCode);
                invoice.setSpotCode(spotCode);
                invoice.setMoney(money);
                invoice.setState(Invoice.STATE_WAIT);
                invoice.setCreator(longName);
                invoice.setOperator(longName);
                super.save(invoice);
            }
        }
    }

    @Override
    public void addTotal(String spotCode, double money, String longName) throws Exception {
        //查询该中心有没有待开的总票，有的话就不能开
        Invoice invoice = findInvoiceForStateIsWaitBySpotCodeDAO.find(spotCode);
        if(invoice != null){
            throw new BusinessException("该中心还有在等待开的总票，暂时不能提起新的总票申请");
        }

        //查询该学习中心总共交了好多钱, 查询该学习中心已经开过了好多钱的发票
        JSONObject json = findTotalPayAndTotalInvoiceBySpotCodeService.find(spotCode);
        double temp = new BigDecimal(json.get("payTotal").toString()).subtract(new BigDecimal(json.get("invoiceTotal").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        if(money > temp){
            throw new BusinessException("您开票的金额超额了！");
        }
        invoice = new Invoice();
        invoice.setSpotCode(spotCode);
        invoice.setMoney(money);
        invoice.setState(Invoice.STATE_WAIT);
        invoice.setCreator(longName);
        invoice.setOperator(longName);
        super.save(invoice);
    }

    @Override
    public void wxAdd(String studentCode, String title, String money, String spotCode, String longName) throws Exception {
        BigDecimal moneyTotal = new BigDecimal(money);

        //查询该学习中心总共交了好多钱, 查询该学习中心已经开过了好多钱的发票
        JSONObject json = findTotalPayAndTotalInvoiceBySpotCodeService.find(spotCode);
        double temp = new BigDecimal(json.get("payTotal").toString()).subtract(new BigDecimal(json.get("invoiceTotal").toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(moneyTotal.doubleValue() > temp){
            throw new BusinessException("您开票的金额超额了！");
        }

        Invoice invoice = new Invoice();
        invoice.setTitle(title);
        invoice.setStudentCode(studentCode);
        invoice.setSpotCode(spotCode);
        invoice.setMoney(Double.valueOf(money));
        invoice.setState(Invoice.STATE_WAIT);
        invoice.setCreator(longName);
        invoice.setOperator(longName);
        super.save(invoice);

    }
}
