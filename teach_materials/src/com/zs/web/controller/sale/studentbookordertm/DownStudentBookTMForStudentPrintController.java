package com.zs.web.controller.sale.studentbookordertm;

import com.zs.service.sale.studentbookordertm.DownStudentBookOrderTMForStudentPrintService;
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
@RequestMapping(value = "/downStudentBookTMForStudentPrint")
public class DownStudentBookTMForStudentPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(DownStudentBookTMForStudentPrintController.class);

    @Resource
    private DownStudentBookOrderTMForStudentPrintService downStudentBookOrderTMForStudentPrintService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="semesterId") String semesterId,
                       @RequestParam(value="state", required=false, defaultValue="1") String state,
                       @RequestParam(value="operateTime", required=false, defaultValue="") String operateTime,
                       @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                               HttpServletRequest request){
        try{
            String downUrl = "/excelDown/FSD_MX.xls";
            downStudentBookOrderTMForStudentPrintService.down(Long.parseLong(semesterId), Integer.parseInt(state), operateTime, spotCode, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学生发书单明细");
            return "error";
        }
    }
}
