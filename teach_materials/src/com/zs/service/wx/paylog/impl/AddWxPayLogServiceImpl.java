package com.zs.service.wx.paylog.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.wx.paylog.FindWxPayLogByCodeDAO;
import com.zs.domain.wx.WxPayLog;
import com.zs.service.wx.paylog.AddWxPayLogService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/10/27.
 */
@Service("addWxPayLogService")
public class AddWxPayLogServiceImpl extends EntityServiceImpl<WxPayLog, FindWxPayLogByCodeDAO> implements AddWxPayLogService {

    @Override
    public synchronized void add(WxPayLog wxPayLog) throws Exception {
        super.save(wxPayLog);
    }
}
