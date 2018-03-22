package com.zs.service.finance.refund;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.finance.Refund;

/**
 * Created by Allen on 2016/1/11.
 */
public interface FindRefundByCodeService extends EntityService<Refund> {
    public Refund find(String code) throws Exception;
}
