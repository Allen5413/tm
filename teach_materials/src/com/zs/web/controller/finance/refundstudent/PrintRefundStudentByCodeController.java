package com.zs.web.controller.finance.refundstudent;

import com.zs.service.finance.refundstudent.PrintRefundStudentByCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/1/10 0010.
 */
@Controller
@RequestMapping(value = "/printRefundStudentByCode")
public class PrintRefundStudentByCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(PrintRefundStudentByCodeController.class);

    @Resource
    private PrintRefundStudentByCodeService printRefundStudentByCodeService;


    @RequestMapping(value = "print")
    public String print(@RequestParam("code") String code,
                       HttpServletRequest request){
        try{
            List<Map<String, Object>> list = printRefundStudentByCodeService.print(code, UserTools.getLoginUserForName(request));
            if(null != list && 0 < list.size()){
                BigDecimal totalPrice = new BigDecimal("0");
                for(Map<String, Object> map : list){
                    totalPrice = totalPrice.add(new BigDecimal(map.get("money").toString())).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                request.setAttribute("totalPrice", totalPrice);
            }
            request.setAttribute("list", list);
            return "finance/refundStudentPrint";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印退款申请记录明细");
            return "error";
        }
    }

    @RequestMapping(value = "printBatch")
    public String printBatch(@RequestParam("codes") String[] codes,
                        HttpServletRequest request){
        try{
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            BigDecimal totalPrice = new BigDecimal("0");
            for(String code : codes) {
                List<Map<String, Object>> list2 = printRefundStudentByCodeService.print(code, UserTools.getLoginUserForName(request));
                if (null != list2 && 0 < list2.size()) {
                    BigDecimal totalPrice2 = new BigDecimal("0");
                    for (Map<String, Object> map : list2) {
                        totalPrice2 = totalPrice2.add(new BigDecimal(map.get("money").toString())).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    totalPrice = totalPrice.add(totalPrice2).setScale(2, BigDecimal.ROUND_HALF_UP);
                    list.addAll(list2);
                }
            }
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("list", list);
            return "finance/refundStudentPrint";
        }
        catch(Exception e){
            super.outputException(request, e, log, "批量打印退款申请记录明细");
            return "error";
        }
    }
}
