package com.zs.web.controller.orderbook.purchaseorderteachmaterial;

import com.zs.service.orderbook.purchaseorderteachmaterial.FindPurchaseOrderTeachMaterialListByOrderCodeService;
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
@RequestMapping(value = "/printPurchaseOrderTM")
public class PrintPurchaseOrderTeachMaterialController extends LoggerController {
    private static Logger log = Logger.getLogger(PrintPurchaseOrderTeachMaterialController.class);

    @Resource
    private FindPurchaseOrderTeachMaterialListByOrderCodeService findPurchaseOrderTeachMaterialListByOrderCodeService;

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
            JSONArray resultJson = findPurchaseOrderTeachMaterialListByOrderCodeService.getPurchaseOrderTeachMaterialListByOrderCode(orderCode, semesterId);
            request.setAttribute("resultJson", resultJson);
            request.setAttribute("orderCode", orderCode);
            return "orderBook/printPurchaseOrderTM";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印订单下的教材明细");
            return "error";
        }
    }
}
