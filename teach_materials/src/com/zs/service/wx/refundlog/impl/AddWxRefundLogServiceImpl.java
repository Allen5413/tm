package com.zs.service.wx.refundlog.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.wx.refundlog.FindWxRefundLogForMaxCodeDAO;
import com.zs.domain.wx.WxRefundLog;
import com.zs.service.wx.refundlog.AddWxRefundLogService;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2015/10/27.
 */
@Service("addWxRefundLogService")
public class AddWxRefundLogServiceImpl extends EntityServiceImpl<WxRefundLog, FindWxRefundLogForMaxCodeDAO> implements AddWxRefundLogService {

    @Override
    public synchronized void add(WxRefundLog wxRefundLog) throws Exception {
        super.save(wxRefundLog);
    }
}
