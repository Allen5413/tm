package com.zs.service.finance.refundstudent;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.RefundStudent;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/1/11.
 */
public interface AuditRefundStudentService extends EntityService<RefundStudent> {
    public void audit(HttpServletRequest request, String code, String[] idDetails, String[] auditPass)throws Exception;

    public void setSpotExpenseOth(String code)throws Exception;
}
