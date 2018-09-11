package com.zs.service.wx.refundlog;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.wx.WxPayLog;
import com.zs.domain.wx.WxRefundLog;

/**
 * Created by Allen on 2016/7/8.
 */
public interface FindWxRefundLogForMaxCodeService extends EntityService<WxRefundLog> {
    public String find(String timeStamp)throws Exception;
}
