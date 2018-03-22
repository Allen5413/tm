package com.zs.web.controller.sale.oncepurchaseordertm;

import com.zs.service.sale.oncepurchaseordertm.FindOncePurchaseOrderTMListByOrderCodeService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 打印订单下的教材明细
 * Created by Allen on 2015/5/12.
 */
@Controller
@RequestMapping(value = "/printOncePurchaseOrderTM")
public class PrintOncePurchaseOrderTMController extends LoggerController {
    private static Logger log = Logger.getLogger(PrintOncePurchaseOrderTMController.class);

    @Resource
    private FindOncePurchaseOrderTMListByOrderCodeService findOncePurchaseOrderTMListByOrderCodeService;

    /**
     * 打印订单明细
     * @param orderCode
     * @param semesterId
     * @param request
     * @return
     */
    @RequestMapping(value = "doPrintPurchaseOrderTM")
    public String doPrintPurchaseOrderTM(@RequestParam(value="orderCode") String orderCode,
                                       @RequestParam(value="semesterId") Long semesterId,
                                       HttpServletRequest request){
        try{
            JSONArray resultJson = findOncePurchaseOrderTMListByOrderCodeService.find(orderCode, semesterId);
            request.setAttribute("resultJson", resultJson);
            request.setAttribute("orderCode", orderCode);
            return "onceOrder/printOncePurchaseOrderTM";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印采购单下的教材明细");
            return "error";
        }
    }
}
