package com.zs.dao.wx.refundlog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.wx.WxRefundLog;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/10/27.
 */
public interface FindWxRefundLogForMaxCodeDAO extends EntityJpaDao<WxRefundLog, Long> {
    @Query(nativeQuery = true, value = "select * from wx_refund_log where order_code like ?1 order by order_code desc limit 1")
    public WxRefundLog find(String timeStamp);
}
