package com.zs.service.wx.refundlog;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.wx.WxRefundLog;

/**
 * Created by Allen on 2015/10/27.
 */
public interface AddWxRefundLogService extends EntityService<WxRefundLog> {
    public void add(WxRefundLog wxRefundLog)throws Exception;
}
