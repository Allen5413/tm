package com.zs.service.finance.refund.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.refund.FindRefundByCodeDAO;
import com.zs.dao.finance.refund.RefundDAO;
import com.zs.domain.finance.Refund;
import com.zs.service.finance.refund.FindRefundByCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/1/11.
 */
@Service("findRefundByCodeService")
public class FindRefundByCodeServiceImpl extends EntityServiceImpl<Refund, RefundDAO> implements FindRefundByCodeService {

    @Resource
    private FindRefundByCodeDAO findRefundByCodeDAO;

    @Override
    public Refund find(String code) throws Exception {
        return findRefundByCodeDAO.find(code);
    }
}
