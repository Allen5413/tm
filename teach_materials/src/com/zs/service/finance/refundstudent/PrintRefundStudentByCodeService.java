package com.zs.service.finance.refundstudent;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.RefundStudent;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/10 0010.
 */
public interface PrintRefundStudentByCodeService extends EntityService<RefundStudent> {
    public List<Map<String, Object>> print(String code, String userName);
}
