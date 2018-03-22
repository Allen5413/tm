package com.zs.service.statis;

import net.sf.json.JSONArray;

/**
 * Created by Allen on 2015/9/29.
 */
public interface FindIssueChannelPayService {
    /**
     * 统计发行商应付款
     * @return
     * @throws Exception
     */
    public JSONArray findIssueChannelPay()throws Exception;
}
