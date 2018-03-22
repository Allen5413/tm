package com.zs.web.controller.sale.onceordertm;

import com.zs.service.sale.onceordertm.FindOnceOrderTMPrintByOrderIdService;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderTMPrintByOrderIdService;
import com.zs.tools.DateTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/9/9.
 */
@Controller
@RequestMapping(value = "/findOnceOrderTMPrintByOrderId")
public class FindOnceOrderTMPrintByOrderIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindOnceOrderTMPrintByOrderIdController.class);

    @Resource
    private FindOnceOrderTMPrintByOrderIdService findOnceOrderTMPrintByOrderIdService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="ids") String[] ids,
                       HttpServletRequest request){
        try{
            findOnceOrderTMPrintByOrderIdService.editPrintSort(ids);
            JSONObject result = findOnceOrderTMPrintByOrderIdService.find(ids);
            request.setAttribute("resultJson", result);
            request.setAttribute("nowDate", DateTools.getShortNowTime());
            return "onceOrder/onceOrderTMPrintList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印学生领书单");
            return "error";
        }
    }
}
