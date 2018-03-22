package com.zs.service.finance.invoice;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Invoice;

import java.util.List;

/**
 * Created by Allen on 2016/7/14.
 */
public interface FindInvoiceByStudentCodeService extends EntityService<Invoice> {
    public List<Invoice> find(String studentCode);
}
