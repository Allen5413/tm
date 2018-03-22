package com.zs.service.wx.notify.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.wx.notifylog.FindNotifyByOutTradeNoDAO;
import com.zs.domain.wx.WxNotifyLog;
import com.zs.service.wx.notify.FindNotifyByOutTradeNoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
@Service("findNotifyByOutTradeNoService")
public class FindNotifyByOutTradeNoServiceImpl extends EntityServiceImpl<WxNotifyLog, FindNotifyByOutTradeNoDAO> implements FindNotifyByOutTradeNoService {

    @Resource
    private FindNotifyByOutTradeNoDAO findNotifyByOutTradeNoDAO;

    @Override
    public List<WxNotifyLog> find(String code) {
        return findNotifyByOutTradeNoDAO.find(code);
    }
}
