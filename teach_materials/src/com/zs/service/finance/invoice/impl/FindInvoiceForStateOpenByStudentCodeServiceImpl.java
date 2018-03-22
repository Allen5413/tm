package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.invoice.FindInvoiceForStateOpenByStudentCodeDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.FindInvoiceForStateOpenByStudentCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2016/5/6.
 */
@Service("findInvoiceForStateOpenByStudentCodeService")
public class FindInvoiceForStateOpenByStudentCodeServiceImpl extends EntityServiceImpl<Invoice, FindInvoiceForStateOpenByStudentCodeDAO> implements FindInvoiceForStateOpenByStudentCodeService {

    @Resource
    private FindInvoiceForStateOpenByStudentCodeDAO findInvoiceForStateOpenByStudentCodeDAO;

    @Override
    public List<Invoice> find(String studentCode) {
        return findInvoiceForStateOpenByStudentCodeDAO.find(studentCode);
    }
}
