package com.zs.web.controller.finance.studentexpensepay;

import com.zs.service.finance.studentexpensebuy.DownStuEBService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/10/14.
 */
@Controller
@RequestMapping(value = "/downStuEB")
public class DownStuEBController extends LoggerController {
    private static Logger log = Logger.getLogger(DownStuEBController.class);

    @Resource
    private DownStuEBService downStuEBService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam("code") String code,
                       HttpServletRequest request){
        try{
            String downUrl = "/excelDown/student.xls";
            downStuEBService.down(code, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载学生费用明细");
            return "error";
        }
    }
}
