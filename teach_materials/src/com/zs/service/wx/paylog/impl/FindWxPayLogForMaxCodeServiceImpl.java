package com.zs.service.wx.paylog.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.wx.paylog.FindWxPayLogForMaxCodeDAO;
import com.zs.domain.wx.WxPayLog;
import com.zs.service.wx.paylog.FindWxPayLogForMaxCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/7/8.
 */
@Service("findWxPayLogForMaxCodeService")
public class FindWxPayLogForMaxCodeServiceImpl extends EntityServiceImpl<WxPayLog, FindWxPayLogForMaxCodeDAO> implements FindWxPayLogForMaxCodeService {

    @Resource
    private FindWxPayLogForMaxCodeDAO findWxPayLogForMaxCodeDAO;

    @Override
    public String find(String timeStamp) throws Exception {
        WxPayLog wxPayLog = findWxPayLogForMaxCodeDAO.find(timeStamp+"%");
        if(null != wxPayLog && !StringUtils.isEmpty(wxPayLog.getOrderCode())){
            String orderCode = wxPayLog.getOrderCode();
            String sort = orderCode.substring(orderCode.length()-3, orderCode.length());
            int num = Integer.parseInt(sort)+1;
            return String.format("%03d", num);
        }else{
            return "001";
        }
    }
}
