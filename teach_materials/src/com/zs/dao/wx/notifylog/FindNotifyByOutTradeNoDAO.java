package com.zs.dao.wx.notifylog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.wx.WxNotifyLog;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2016/5/3.
 */
public interface FindNotifyByOutTradeNoDAO extends EntityJpaDao<WxNotifyLog, Long> {
    @Query("from WxNotifyLog where outTradeNo = ?1")
    public List<WxNotifyLog> find(String outTradeNo);
}
