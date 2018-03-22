package com.zs.web.controller.sale.studentbookorder;

import com.zs.service.sale.studentbookorder.DownStudentBookOrderForSpotPrintService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/8/12.
 */
@Controller
@RequestMapping(value = "/downStudentBookForSpotPrint")
public class DownStudentBookForSpotPrintController extends LoggerController {
    private static Logger log = Logger.getLogger(DownStudentBookForSpotPrintController.class);

    @Resource
    private DownStudentBookOrderForSpotPrintService downStudentBookOrderForSpotPrintService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="spotCode") String spotCode,
                       @RequestParam(value="state") String state,
                       @RequestParam(value="semesterId") String semesterId,
                       @RequestParam(value="operateTime", required=false, defaultValue="") String operateTime,
                       HttpServletRequest request){
        try{
            String downUrl = spotCode + "FSD.xls";
            downStudentBookOrderForSpotPrintService.down(spotCode, semesterId, state, operateTime, request.getRealPath("") + "/excelDown/" + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学习中心发书单");
            return "error";
        }
    }
}
