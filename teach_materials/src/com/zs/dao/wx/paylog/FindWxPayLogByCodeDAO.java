package com.zs.dao.wx.paylog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.wx.WxPayLog;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2015/10/28.
 */
public interface FindWxPayLogByCodeDAO extends EntityJpaDao<WxPayLog, Long> {
    @Query("from WxPayLog where orderCode = ?1")
    public WxPayLog find(String code);
}
