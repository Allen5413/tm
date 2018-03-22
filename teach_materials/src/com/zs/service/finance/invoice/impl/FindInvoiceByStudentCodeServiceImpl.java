package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.invoice.FindInvoiceByStudentCodeDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.FindInvoiceByStudentCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2016/7/14.
 */
@Service("findInvoiceByStudentCodeService")
public class FindInvoiceByStudentCodeServiceImpl extends EntityServiceImpl<Invoice, FindInvoiceByStudentCodeDAO> implements FindInvoiceByStudentCodeService {

    @Resource
    private FindInvoiceByStudentCodeDAO findInvoiceByStudentCodeDAO;

    @Override
    public List<Invoice> find(String studentCode) {
        return findInvoiceByStudentCodeDAO.find(studentCode);
    }
}
