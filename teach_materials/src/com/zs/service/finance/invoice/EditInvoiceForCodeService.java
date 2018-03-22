package com.zs.service.finance.invoice;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Invoice;

/**
 * Created by Allen on 2016/5/6.
 */
public interface EditInvoiceForCodeService extends EntityService<Invoice> {
    public void edit(long id, String code, String openTime, String loginName)throws Exception;
}
