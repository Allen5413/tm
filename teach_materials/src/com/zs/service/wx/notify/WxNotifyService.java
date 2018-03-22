package com.zs.service.wx.notify;

import java.util.Map;

/**
 * Created by Allen on 2015/10/28.
 */
public interface WxNotifyService {
    public void notify(Map<String, String> params, int isPC) throws Exception;

    public void ebookNotify(Map<String, String> params) throws Exception;
}
