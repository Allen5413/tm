package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.invoice.FindInvoiceByCodeDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.EditInvoiceForCodeService;
import com.zs.tools.DateTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Allen on 2016/5/6.
 */
@Service("editInvoiceForCodeService")
public class EditInvoiceForCodeServiceImpl extends EntityServiceImpl<Invoice, FindInvoiceByCodeDAO> implements EditInvoiceForCodeService {

    @Resource
    private FindInvoiceByCodeDAO findInvoiceByCodeDAO;

    @Override
    public void edit(long id, String code, String openTime, String loginName) throws Exception {
        Invoice invoice = super.get(id);
        if(null == invoice){
            throw new BusinessException("没有找到发票申请数据");
        }

        if(StringUtils.isEmpty(code) && StringUtils.isEmpty(openTime)){
            throw new BusinessException("发票号和开票时间不能都为空");
        }

        if(!StringUtils.isEmpty(code)) {
            Invoice invoice2 = findInvoiceByCodeDAO.find(code);
            if (null != invoice2 && !invoice2.getCode().equals(invoice.getCode())) {
                throw new BusinessException("发票号：" + code + ", 已经存在");
            }
        }
        invoice.setCode(code);
        invoice.setState(Invoice.STATE_OPEN);
        if(StringUtils.isEmpty(openTime)){
            invoice.setOpenTime(null);
        }else{
            invoice.setOpenTime(DateTools.getFormatDate(openTime, "yyyy-MM-dd"));
        }
        invoice.setOperator(loginName);
        invoice.setOperateTime(DateTools.getLongNowTime());
        super.update(invoice);
    }
}
