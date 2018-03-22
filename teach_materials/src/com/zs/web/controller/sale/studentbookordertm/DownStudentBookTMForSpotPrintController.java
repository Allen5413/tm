package com.zs.web.controller.sale.studentbookordertm;

import com.zs.service.sale.studentbookordertm.DownStudentBookOrderTMForSpotPrintService;
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
 * Created by Allen on 2015/8/13.
 */
@Controller
@RequestMapping(value = "/downStudentBookTMForSpotPrint")
public class DownStudentBookTMForSpotPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(DownStudentBookTMForSpotPrintController.class);

    @Resource
    private DownStudentBookOrderTMForSpotPrintService downStudentBookOrderTMForSpotPrintService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="spotCode") String spotCode,
                       @RequestParam(value="state") String state,
                       @RequestParam(value="semesterId") String semesterId,
                       @RequestParam(value="operateTime", required=false, defaultValue="") String operateTime,
                       HttpServletRequest request,
                       HttpServletResponse response){
        try{
            String downUrl = "/excelDown/" + spotCode + "FSD_MX.xls";
            downStudentBookOrderTMForSpotPrintService.down(spotCode, semesterId, state, operateTime, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学习中心发书单明细");
            return "error";
        }
    }
}
