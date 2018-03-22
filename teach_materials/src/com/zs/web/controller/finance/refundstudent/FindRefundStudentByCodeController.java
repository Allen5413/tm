package com.zs.web.controller.finance.refundstudent;

import com.zs.domain.finance.Refund;
import com.zs.service.finance.refund.FindRefundByCodeService;
import com.zs.service.finance.refundstudent.FindRefundStudentByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/7.
 */
@Controller
@RequestMapping(value = "/findRefundStudentByCode")
public class FindRefundStudentByCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(FindRefundStudentByCodeController.class);

    @Resource
    private FindRefundStudentByCodeService findRefundStudentByCodeService;
    @Resource
    private FindRefundByCodeService findRefundByCodeService;


    @RequestMapping(value = "find")
    public String find(@RequestParam("code") String code,
                       HttpServletRequest request){
        try{
            Map<String, Object> map = findRefundStudentByCodeService.find(code);
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
            Refund refund = findRefundByCodeService.find(code);

            request.setAttribute("list", list);
            request.setAttribute("totalMoney", map.get("totalMoney"));
            request.setAttribute("passTotalMoney", map.get("passTotalMoney"));
            request.setAttribute("notPassTotalMoney", map.get("notPassTotalMoney"));
            request.setAttribute("refund", refund);
            return "finance/refundStudentList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "查询退款申请记录明细");
            return "error";
        }
    }
}
