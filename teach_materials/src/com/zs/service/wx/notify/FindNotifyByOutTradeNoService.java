package com.zs.service.wx.notify;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.wx.WxNotifyLog;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
public interface FindNotifyByOutTradeNoService extends EntityService<WxNotifyLog> {
    public List<WxNotifyLog> find(String code);
}
