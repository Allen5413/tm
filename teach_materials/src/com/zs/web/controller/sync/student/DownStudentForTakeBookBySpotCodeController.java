package com.zs.web.controller.sync.student;

import com.feinno.framework.common.exception.BusinessException;
import com.zs.service.sync.student.DownStudentForTakeBookBySpotCodeService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import org.apache.commons.lang.StringUtils;
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
 * Created by Allen on 2015/9/10.
 */
@Controller
@RequestMapping(value = "/downStudentForTakeBookBySpotCode")
public class DownStudentForTakeBookBySpotCodeController extends LoggerController {
    private static Logger log = Logger.getLogger(DownStudentForTakeBookBySpotCodeController.class);

    @Resource
    private DownStudentForTakeBookBySpotCodeService downStudentForTakeBookBySpotCodeService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="spotCode", required = false, defaultValue = "") String spotCode,
                       HttpServletRequest request,
                       HttpServletResponse response){
        try{
            if(StringUtils.isEmpty(spotCode)){
                spotCode = UserTools.getLoginUserForSpotCode(request);
            }
            if(StringUtils.isEmpty(spotCode)){
                throw new BusinessException("没有找到学习中心！");
            }
            String downUrl = "/excelDown/" + spotCode + ".xls";
            downStudentForTakeBookBySpotCodeService.down(spotCode, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载缴费领书学生信息表");
            return "error";
        }
    }
}
