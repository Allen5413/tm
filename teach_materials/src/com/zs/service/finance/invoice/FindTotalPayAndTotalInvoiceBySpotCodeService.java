package com.zs.service.finance.invoice;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Invoice;
import net.sf.json.JSONObject;

/**
 * 查询一个中心的所有缴费金额和开票金额
 * Created by Allen on 2016/5/9.
 */
public interface FindTotalPayAndTotalInvoiceBySpotCodeService extends EntityService<Invoice> {
    public JSONObject find(String spotCode);
}
