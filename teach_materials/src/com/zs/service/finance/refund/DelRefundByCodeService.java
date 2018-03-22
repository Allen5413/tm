package com.zs.service.finance.refund;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Refund;

/**
 * Created by Allen on 2016/12/12.
 */
public interface DelRefundByCodeService extends EntityService<Refund> {
    public void del(String code)throws Exception;
}
