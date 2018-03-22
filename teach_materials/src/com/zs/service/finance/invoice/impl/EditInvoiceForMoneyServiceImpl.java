package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.invoice.InvoiceDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.EditInvoiceForMoneyService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/5/6.
 */
@Service("editInvoiceForMoneyService")
public class EditInvoiceForMoneyServiceImpl extends EntityServiceImpl<Invoice, InvoiceDAO> implements EditInvoiceForMoneyService {

    @Override
    public void edit(long id, double money, String loginName) throws Exception {
        Invoice invoice = super.get(id);
        if(null == invoice){
            throw new BusinessException("没有找到发票申请数据");
        }
        invoice.setMoney(money);
        invoice.setOperator(loginName);
        invoice.setOperateTime(DateTools.getLongNowTime());
        super.update(invoice);
    }
}
