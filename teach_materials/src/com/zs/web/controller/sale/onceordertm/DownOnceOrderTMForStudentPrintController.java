package com.zs.web.controller.sale.onceordertm;

import com.zs.service.sale.onceordertm.DownOnceOrderTMForStudentPrintService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/8/13.
 */
@Controller
@RequestMapping(value = "/downOnceOrderTMForStudentPrint")
public class DownOnceOrderTMForStudentPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(DownOnceOrderTMForStudentPrintController.class);

    @Resource
    private DownOnceOrderTMForStudentPrintService downOnceOrderTMForStudentPrintService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="semesterId") String semesterId,
                       @RequestParam(value="state", required=false, defaultValue="2") String state,
                       @RequestParam(value="operateTime", required=false, defaultValue="") String operateTime,
                               HttpServletRequest request){
        try{
            String downUrl = "/excelDown/FSD_MX.xls";
            downOnceOrderTMForStudentPrintService.down(Long.parseLong(semesterId), Integer.parseInt(state), operateTime, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学生发书单明细");
            return "error";
        }
    }
}
