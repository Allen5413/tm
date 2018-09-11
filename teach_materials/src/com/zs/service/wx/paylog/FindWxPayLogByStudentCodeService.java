package com.zs.service.wx.paylog;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.wx.WxPayLog;

import java.util.List;

/**
 * Created by Allen on 2018/9/11.
 */
public interface FindWxPayLogByStudentCodeService extends EntityService<WxPayLog> {
    public List<WxPayLog> find(String studentCode)throws Exception;
}
