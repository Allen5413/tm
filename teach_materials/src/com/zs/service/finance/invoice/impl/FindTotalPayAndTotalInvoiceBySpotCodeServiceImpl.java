package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.invoice.FindSpotInvoiceTotalBySpotCodeDAO;
import com.zs.dao.finance.invoice.InvoiceDAO;
import com.zs.dao.finance.spotexpense.FindSpotPayTotalBySpotCodeDAO;
import com.zs.dao.finance.studentexpense.FindStudentPayTotalBySpotCodeDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.FindTotalPayAndTotalInvoiceBySpotCodeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Allen on 2016/5/9.
 */
@Service("findTotalPayAndTotalInvoiceBySpotCodeService")
public class FindTotalPayAndTotalInvoiceBySpotCodeServiceImpl extends EntityServiceImpl<Invoice, InvoiceDAO> implements FindTotalPayAndTotalInvoiceBySpotCodeService {

    @Resource
    private FindStudentPayTotalBySpotCodeDAO findStudentPayTotalBySpotCodeDAO;
    @Resource
    private FindSpotPayTotalBySpotCodeDAO findSpotPayTotalBySpotCodeDAO;
    @Resource
    private FindSpotInvoiceTotalBySpotCodeDAO findSpotInvoiceTotalBySpotCodeDAO;

    @Override
    public JSONObject find(String spotCode) {
        JSONObject json = new JSONObject();
        //查询该学习中心总共交了好多钱
        double pay = 0;
        double studentPay = findStudentPayTotalBySpotCodeDAO.find(spotCode);
        double spotPay = findSpotPayTotalBySpotCodeDAO.find(spotCode);
        pay = new BigDecimal(studentPay).add(new BigDecimal(spotPay)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //查询该学习中心已经开过了好多钱的发票
        double invoiceMoney = new BigDecimal(findSpotInvoiceTotalBySpotCodeDAO.find(spotCode)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        json.put("payTotal", pay);
        json.put("invoiceTotal", invoiceMoney);
        return json;
    }
}
