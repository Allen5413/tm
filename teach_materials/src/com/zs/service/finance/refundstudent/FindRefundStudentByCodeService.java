package com.zs.service.finance.refundstudent;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.RefundStudent;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/7.
 */
public interface FindRefundStudentByCodeService extends EntityService<RefundStudent> {
    public Map<String, Object> find(String code);
}
