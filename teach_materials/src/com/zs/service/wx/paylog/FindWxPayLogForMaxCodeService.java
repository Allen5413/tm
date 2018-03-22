package com.zs.service.wx.paylog;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.wx.WxPayLog;

/**
 * Created by Allen on 2016/7/8.
 */
public interface FindWxPayLogForMaxCodeService extends EntityService<WxPayLog> {
    public String find(String timeStamp)throws Exception;
}
