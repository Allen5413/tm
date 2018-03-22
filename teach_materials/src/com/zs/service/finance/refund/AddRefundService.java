package com.zs.service.finance.refund;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Refund;

/**
 * 新增申请
 * Created by Allen on 2016/1/5 0005.
 */
public interface AddRefundService extends EntityService<Refund> {
    public void add(String studentMoneyDetails, String bankName, String bankCode, String spotCode, String company, String userName) throws Exception;
}
