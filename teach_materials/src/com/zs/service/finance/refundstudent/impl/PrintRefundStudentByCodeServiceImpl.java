package com.zs.service.finance.refundstudent.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.finance.refund.FindRefundByCodeDAO;
import com.zs.dao.finance.refundlog.RefundLogDAO;
import com.zs.dao.finance.refundstudent.FindRefundStudentByCodeDAO;
import com.zs.domain.finance.Refund;
import com.zs.domain.finance.RefundLog;
import com.zs.domain.finance.RefundStudent;
import com.zs.service.finance.refundstudent.FindRefundStudentByCodeService;
import com.zs.service.finance.refundstudent.PrintRefundStudentByCodeService;
import com.zs.tools.DateTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/10 0010.
 */
@Service("printRefundStudentByCodeService")
public class PrintRefundStudentByCodeServiceImpl extends EntityServiceImpl<RefundStudent, FindRefundStudentByCodeDAO>
        implements PrintRefundStudentByCodeService{

    @Resource
    private FindRefundStudentByCodeService findRefundStudentByCodeService;
    @Resource
    private FindRefundByCodeDAO findRefundByCodeDAO;
    @Resource
    private RefundLogDAO refundLogDAO;

    @Override
    @Transactional
    public List<Map<String, Object>> print(String code, String userName) {
        //查询退款申请
        Refund refund = findRefundByCodeDAO.find(code);
        if(null == code){
            throw new BusinessException("没有找到该退款申请信息");
        }
        Map<String, Object> map = findRefundStudentByCodeService.find(code);
        List<Map<String, Object>> returnList = (List<Map<String, Object>>) map.get("list");
        if(refund.getState() < Refund.STATE_WAIT_UPLOAD) {
            //修改状态为待上传凭证
            refund.setState(Refund.STATE_WAIT_UPLOAD);
            refund.setOperator(userName);
            refund.setOperateTime(DateTools.getLongNowTime());
            findRefundByCodeDAO.update(refund);
            //记录退款申请状态变更记录
            RefundLog refundLog = new RefundLog();
            refundLog.setCode(code);
            refundLog.setState(Refund.STATE_WAIT_UPLOAD);
            refundLog.setOperator(userName);
            refundLogDAO.save(refundLog);
        }
        return returnList;
    }
}
