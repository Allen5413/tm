package com.zs.service.finance.invoice;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Invoice;

/**
 * Created by Allen on 2016/5/4 0004.
 */
public interface AddInvoiceService extends EntityService<Invoice> {
    public void add(String codeMoneys, String spotCode, String longName)throws Exception;

    public void addTotal(String spotCode, double money, String longName)throws Exception;

    public void wxAdd(String studentCode, String title, String money, String spotCode, String longName)throws Exception;
}
