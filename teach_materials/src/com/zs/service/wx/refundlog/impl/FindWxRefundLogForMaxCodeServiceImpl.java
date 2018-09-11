package com.zs.service.wx.refundlog.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.wx.refundlog.FindWxRefundLogForMaxCodeDAO;
import com.zs.domain.wx.WxRefundLog;
import com.zs.service.wx.refundlog.FindWxRefundLogForMaxCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/7/8.
 */
@Service("findWxRefundLogForMaxCodeService")
public class FindWxRefundLogForMaxCodeServiceImpl extends EntityServiceImpl<WxRefundLog, FindWxRefundLogForMaxCodeDAO> implements FindWxRefundLogForMaxCodeService {

    @Resource
    private FindWxRefundLogForMaxCodeDAO findWxRefundLogForMaxCodeDAO;

    @Override
    public String find(String timeStamp) throws Exception {
        WxRefundLog wxRefundLog = findWxRefundLogForMaxCodeDAO.find(timeStamp+"%");
        if(null != wxRefundLog && !StringUtils.isEmpty(wxRefundLog.getOrderCode())){
            String orderCode = wxRefundLog.getOrderCode();
            String sort = orderCode.substring(orderCode.length()-3, orderCode.length());
            int num = Integer.parseInt(sort)+1;
            return String.format("%03d", num);
        }else{
            return "001";
        }
    }
}
