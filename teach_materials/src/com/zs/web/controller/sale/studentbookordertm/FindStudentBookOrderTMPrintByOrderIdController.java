package com.zs.web.controller.sale.studentbookordertm;

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
@RequestMapping(value = "/findStudentBookOrderTMPrintByOrderId")
public class FindStudentBookOrderTMPrintByOrderIdController extends LoggerController {
    private static Logger log = Logger.getLogger(FindStudentBookOrderTMPrintByOrderIdController.class);

    @Resource
    private FindStudentBookOrderTMPrintByOrderIdService findStudentBookOrderTMPrintByOrderIdService;

    @RequestMapping(value = "find")
    public String find(@RequestParam(value="ids") String[] ids,
                       HttpServletRequest request){
        try{
            findStudentBookOrderTMPrintByOrderIdService.editPrintSort(ids);
            JSONObject result = findStudentBookOrderTMPrintByOrderIdService.findStudentBookOrderTMPrintByOrderId(ids);
            request.setAttribute("resultJson", result);
            request.setAttribute("nowDate", DateTools.getShortNowTime());
            return "studentOrder/studentOrderTMPrintList";
        }
        catch(Exception e){
            super.outputException(request, e, log, "打印学生领书单");
            return "error";
        }
    }
}
