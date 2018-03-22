package com.zs.service.finance.invoice;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Invoice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/5/6.
 */
public interface UploadInvoiceService extends EntityService<Invoice> {
    public void upload(HttpServletRequest request)throws Exception;
}
