package com.zs.service.finance.refund.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.refund.DelRefundByCodeDAO;
import com.zs.dao.finance.refundlog.DelRefundLogByCodeDAO;
import com.zs.dao.finance.refundstudent.DelRefundStudentByCodeDAO;
import com.zs.domain.finance.Refund;
import com.zs.service.finance.refund.DelRefundByCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Allen on 2016/12/12.
 */
@Service("delRefundByCodeService")
public class DelRefundByCodeServiceImpl extends EntityServiceImpl<Refund, DelRefundByCodeDAO> implements DelRefundByCodeService {

    @Resource
    private DelRefundByCodeDAO delRefundByCodeDAO;
    @Resource
    private DelRefundLogByCodeDAO delRefundLogByCodeDAO;
    @Resource
    private DelRefundStudentByCodeDAO delRefundStudentByCodeDAO;

    @Override
    @Transactional
    public void del(String code) throws Exception {
        delRefundStudentByCodeDAO.del(code);
        delRefundLogByCodeDAO.del(code);
        delRefundByCodeDAO.del(code);
    }
}
