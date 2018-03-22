package com.zs.dao.wx.paylog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.wx.WxPayLog;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/10/27.
 */
public interface FindWxPayLogForMaxCodeDAO extends EntityJpaDao<WxPayLog, Long> {
    @Query(nativeQuery = true, value = "select * from wx_pay_log where order_code like ?1 order by order_code desc limit 1")
    public WxPayLog find(String timeStamp);
}
