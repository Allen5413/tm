package com.zs.web.controller.finance.refundstudent;

import com.zs.domain.finance.Refund;
import com.zs.service.finance.refund.FindRefundByCodeService;
import com.zs.service.finance.refundstudent.AuditRefundStudentService;
import com.zs.service.finance.refundstudent.FindRefundStudentByCodeService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Allen on 2016/1/5 0005.
 */
@Controller
@RequestMapping(value = "/auditRefundStudent")
public class AuditRefundStudentController extends LoggerController {
    private static Logger log = Logger.getLogger(PrintRefundStudentByCodeController.class);

    @Resource
    private FindRefundStudentByCodeService findRefundStudentByCodeService;
    @Resource
    private FindRefundByCodeService findRefundByCodeService;
    @Resource
    private AuditRefundStudentService auditRefundStudentService;


    @RequestMapping(value = "open")
    public String open(@RequestParam("code") String code,
                        HttpServletRequest request){
        try{
            Refund refund = findRefundByCodeService.find(code);
            Map<String, Object> map = findRefundStudentByCodeService.find(code);
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
            request.setAttribute("list", list);
            request.setAttribute("refund", refund);
            return "finance/refundStudentAudit";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打开审核退款申请");
            return "error";
        }
    }

    /**
     * 审核
     * @param code          退款批次编号
     * @param idDetails     退款明细id对应的审核说明
     * @param auditPass     审核通过的明细
     * @param request
     * @return
     */
    @RequestMapping(value = "audit")
    @ResponseBody
    public JSONObject audit(@RequestParam("code") String code,
                            @RequestParam("spotCode") String spotCode,
                        @RequestParam("idDetails") String[] idDetails,
                        @RequestParam(value = "cb", required = false) String[] auditPass,
                        HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            auditRefundStudentService.audit(request, code, idDetails, auditPass);
            auditRefundStudentService.setSpotExpenseOth(spotCode);
            jsonObject.put("state", 0);
        }
        catch(Exception e){
            String msg = super.outputException(request, e, log, "审核退款申请");
            jsonObject.put("state", 1);
            jsonObject.put("msg", msg);
        }
        return jsonObject;
    }
}
