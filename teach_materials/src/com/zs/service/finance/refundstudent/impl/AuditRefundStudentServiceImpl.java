package com.zs.service.finance.refundstudent.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.refund.FindRefundByCodeDAO;
import com.zs.dao.finance.refundlog.RefundLogDAO;
import com.zs.dao.finance.refundstudent.RefundStudentDAO;
import com.zs.dao.sale.onceorder.EditOnceOrderForEnterNotSendByStudentCodeDAO;
import com.zs.dao.sale.studentbookorder.EditStudentBookOrderForEnterNotSendByStudentCodeDAO;
import com.zs.dao.sync.student.EditStudentIsForeverSendTmByCodeDAO;
import com.zs.domain.finance.Refund;
import com.zs.domain.finance.RefundLog;
import com.zs.domain.finance.RefundStudent;
import com.zs.domain.sync.Student;
import com.zs.service.finance.refundstudent.AuditRefundStudentService;
import com.zs.service.finance.refundstudent.FindRefundStudentByCodeService;
import com.zs.service.finance.spotexpenseoth.SetSpotExpenseOthBySpotCodeService;
import com.zs.service.finance.studentexpensepay.RefundStudentExpensePayByCodeService;
import com.zs.tools.DateTools;
import com.zs.tools.PropertiesTools;
import com.zs.tools.UpLoadFileTools;
import com.zs.tools.UserTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/11.
 */
@Service("auditRefundStudentService")
public class AuditRefundStudentServiceImpl extends EntityServiceImpl<RefundStudent, RefundStudentDAO> implements AuditRefundStudentService {

    @Resource
    private FindRefundByCodeDAO findRefundByCodeDAO;
    @Resource
    private FindRefundStudentByCodeService findRefundStudentByCodeService;
    @Resource
    private RefundLogDAO refundLogDAO;
    @Resource
    private RefundStudentExpensePayByCodeService refundStudentExpensePayByCodeService;
    @Resource
    private SetSpotExpenseOthBySpotCodeService setSpotExpenseOthBySpotCodeService;
    @Resource
    private EditStudentBookOrderForEnterNotSendByStudentCodeDAO editStudentBookOrderForEnterNotSendByStudentCodeDAO;
    @Resource
    private EditOnceOrderForEnterNotSendByStudentCodeDAO editOnceOrderForEnterNotSendByStudentCodeDAO;
    @Resource
    private EditStudentIsForeverSendTmByCodeDAO editStudentIsForeverSendTmByCodeDAO;

    @Override
    @Transactional
    public void audit(HttpServletRequest request, String code, String[] idDetails, String[] auditPass)throws Exception {
        Refund refund = findRefundByCodeDAO.find(code);
        if(null == refund){
            throw new BusinessException("没有找到退款申请信息");
        }
        Map<String, Object> refundStudentMap = findRefundStudentByCodeService.find(code);
        List<Map<String, Object>> list = (List<Map<String, Object>>) refundStudentMap.get("list");
        if(null == list || 1 > list.size()){
            throw new BusinessException("没有找到退款申请明细信息");
        }

        String userName = UserTools.getLoginUserForName(request);
        Date operateTime = DateTools.getLongNowTime();

        MultipartRequest mulReu = (MultipartRequest)request;
        String uploadPath = new PropertiesTools("resource/commons.properties").getProperty("student_refund_audit_img_path");
        //处理上传图片
        String imagUrl = UpLoadFileTools.uploadImg(request, mulReu.getFiles("auditImg"), "jpg|png|jpeg", 400, 5, uploadPath + code);

        //修改退款申请状态
        refund.setState(Refund.STATE_AUDIT);
        refund.setRefundConfirmImg(imagUrl);
        refund.setOperator(userName);
        refund.setOperateTime(operateTime);
        findRefundByCodeDAO.update(refund);

        //记录状态变更日志
        RefundLog refundLog = new RefundLog();
        refundLog.setCode(code);
        refundLog.setState(Refund.STATE_AUDIT);
        refundLog.setOperator(userName);
        refundLogDAO.save(refundLog);

        //处理退款明细
        for(Map<String, Object> map : list){
            long id = Long.parseLong(map.get("id").toString());
            RefundStudent refundStudent = super.get(id);

            //检查是否审核通过
            boolean isPass = this.isPass(id, auditPass);
            if(isPass){
                refundStudent.setState(RefundStudent.STATE_PASS);
                //扣除学生费用
                refundStudentExpensePayByCodeService.refund(refundStudent.getStudentCode(), refundStudent.getMoney(), userName);
                //把当前该学生还未发出的订单，改为未确认；学生是否永久发书标识改为：否
                editStudentBookOrderForEnterNotSendByStudentCodeDAO.edit(refundStudent.getStudentCode());
                editOnceOrderForEnterNotSendByStudentCodeDAO.edit(refundStudent.getStudentCode());
                editStudentIsForeverSendTmByCodeDAO.edit(Student.IS_FOREVER_SNEDTM_NOT, refundStudent.getStudentCode());
            }else{
                refundStudent.setState(RefundStudent.STATE_NOT_PASS);
            }
            //获取审核说明
            String detail = this.getDetail(id, idDetails);

            refundStudent.setAuditDetail(detail);
            refundStudent.setOperator(userName);
            refundStudent.setOperateTime(operateTime);
            super.update(refundStudent);
        }
    }

    @Override
    @Transactional
    public void setSpotExpenseOth(String code)throws Exception{
        //重新计算中心财务信息
        setSpotExpenseOthBySpotCodeService.reset(code);
    }


    protected boolean isPass(long id, String[] auditPass){
        boolean isPass = false;
        if(null != auditPass && 0 < auditPass.length){
            for(String auditPassId : auditPass){
                if(id == Long.parseLong(auditPassId)){
                    isPass = true;
                    break;
                }
            }
        }
        return isPass;
    }

    protected String getDetail(long id, String[] idDetails){
        String result = "";
        if(null != idDetails && 0 < idDetails.length){
            for (String idDetail : idDetails){
                String[] idDetailArray = idDetail.split("_");
                if(null != idDetailArray && 0 <idDetailArray.length){
                    if(2 == idDetailArray.length){
                        long detail_id = Long.parseLong(idDetailArray[0]);
                        String detail = idDetailArray[1];
                        if(id == detail_id){
                            result = detail;
                        }
                    }
                }
            }
        }
        return result;
    }
}
