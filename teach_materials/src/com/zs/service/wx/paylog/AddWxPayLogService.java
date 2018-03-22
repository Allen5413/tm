package com.zs.service.wx.paylog;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.wx.WxPayLog;

/**
 * Created by Allen on 2015/10/27.
 */
public interface AddWxPayLogService extends EntityService<WxPayLog> {
    public void add(WxPayLog wxPayLog)throws Exception;
}
