package com.zs.dao.wx.paylog;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.wx.WxPayLog;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Allen on 2015/10/28.
 */
public interface FindWxPayLogByStudentCodeDAO extends EntityJpaDao<WxPayLog, Long> {
    @Query("from WxPayLog where studentCode = ?1")
    public List<WxPayLog> find(String studentCode);
}
