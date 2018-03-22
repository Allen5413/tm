package com.zs.service.finance.refund.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.refund.FindRefundByCodeDAO;
import com.zs.dao.finance.refund.RefundDAO;
import com.zs.dao.finance.refundlog.RefundLogDAO;
import com.zs.domain.finance.Refund;
import com.zs.domain.finance.RefundLog;
import com.zs.service.finance.refund.UploadApplyImgService;
import com.zs.tools.DateTools;
import com.zs.tools.PropertiesTools;
import com.zs.tools.UpLoadFileTools;
import com.zs.tools.UserTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/1/11.
 */
@Service("uploadApplyImgService")
public class UploadApplyImgServiceImpl extends EntityServiceImpl<Refund,RefundDAO> implements UploadApplyImgService{

    @Resource
    private FindRefundByCodeDAO findRefundByCodeDAO;
    @Resource
    private RefundLogDAO refundLogDAO;

    @Override
    @Transactional
    public void upload(String code, HttpServletRequest request) throws Exception {
        Refund refund = findRefundByCodeDAO.find(code);
        if(null == refund){
            throw new BusinessException("没有找到该退款申请信息！");
        }

        MultipartRequest mulReu = (MultipartRequest)request;

        String uploadPath = new PropertiesTools("resource/commons.properties").getProperty("student_refund_apply_img_path");

        //处理上传图片
        String imagUrl = UpLoadFileTools.uploadImg(request, mulReu.getFiles("applyImg"), "jpg|png|jpeg", 400, 5, uploadPath+code);

        //修改退款申请
        refund.setState(Refund.STATE_WAIT_AUDIT);
        refund.setRefundApplyImg(imagUrl);
        refund.setOperator(UserTools.getLoginUserForName(request));
        refund.setOperateTime(DateTools.getLongNowTime());
        super.update(refund);

        //记录退款流程状态变更日志
        RefundLog refundLog = new RefundLog();
        refundLog.setCode(code);
        refundLog.setState(Refund.STATE_WAIT_AUDIT);
        refundLog.setOperator(UserTools.getLoginUserForName(request));
        refundLogDAO.save(refundLog);
    }
}
