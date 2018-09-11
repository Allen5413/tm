package com.zs.service.wx.paylog.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.wx.paylog.FindWxPayLogByStudentCodeDAO;
import com.zs.dao.wx.paylog.FindWxPayLogForMaxCodeDAO;
import com.zs.domain.wx.WxPayLog;
import com.zs.service.wx.paylog.FindWxPayLogByStudentCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Allen on 2018/9/11.
 */
@Service("findWxPayLogByStudentCodeService")
public class FindWxPayLogByStudentCodeServiceImpl extends EntityServiceImpl<WxPayLog, FindWxPayLogForMaxCodeDAO> implements FindWxPayLogByStudentCodeService {

    @Resource
    private FindWxPayLogByStudentCodeDAO findWxPayLogByStudentCodeDAO;

    @Override
    public List<WxPayLog> find(String studentCode) throws Exception {
        return findWxPayLogByStudentCodeDAO.find(studentCode);
    }
}
