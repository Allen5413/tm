package com.zs.web.controller.sale.oncepurchaseordertm;

import com.zs.service.sale.oncepurchaseordertm.DownOncePurchaseOrderTMService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/6/3.
 */
@Controller
@RequestMapping(value = "/downOncePurchaseOrderTM")
public class DownOncePurchaseOrderTMController extends LoggerController {
    private static Logger log = Logger.getLogger(DownOncePurchaseOrderTMController.class);

    @Resource
    private DownOncePurchaseOrderTMService downOncePurchaseOrderTMService;

    @RequestMapping(value = "downPurchaseOrderTMExcel")
    @ResponseBody
    public String downPurchaseOrderTMExcel(@RequestParam(value="orderCode") String orderCode,
                                                   @RequestParam(value="channelId") Long channelId,
                                                   @RequestParam(value="semesterId") Long semesterId,
                                                   HttpServletRequest request){
        try{
            String downUrl = "/excelDown/"+orderCode+".xls";
            downOncePurchaseOrderTMService.down(orderCode, semesterId, channelId, request.getRealPath("")+downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载采购单下的教材明细");
            return "error";
        }
    }
}
