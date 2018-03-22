package com.zs.web.controller.placeorder.ordertm;

import com.zs.service.placeorder.DownPlaceOrderTMForSpotPrintService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2015/8/18.
 */
@Controller
@RequestMapping(value = "/downPlaceOrderTMForSpotPrint")
public class DownPlaceOrderTMForSpotPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(DownPlaceOrderTMForSpotPrintController.class);

    @Resource
    private DownPlaceOrderTMForSpotPrintService downPlaceOrderTMForSpotPrintService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="spotCode") String spotCode,
                       @RequestParam(value="state") String state,
                       @RequestParam(value="semesterId") String semesterId,
                       HttpServletRequest request,
                       HttpServletResponse response){
        try{
            String downUrl = "/excelDown/" + spotCode + "YD_MX.xls";
            downPlaceOrderTMForSpotPrintService.down(spotCode, semesterId, state, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学习中心预订单明细");
            return "error";
        }
    }
}
