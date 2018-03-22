package com.zs.web.controller.statis;

import com.zs.domain.basic.IssueChannel;
import com.zs.domain.basic.Semester;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.service.statis.DownIssueChannelPayDetailService;
import com.zs.service.statis.FindIssueChannelPayDetailService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
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
 * Created by Allen on 2015/11/25 0025.
 */
@Controller
@RequestMapping(value = "/findIssueChannelPayDetail")
public class FindIssueChannelPayDetailController extends LoggerController {
    private static Logger log = Logger.getLogger(FindIssueChannelPayDetailController.class);

    @Resource
    private FindIssueChannelPayDetailService findIssueChannelPayDetailService;
    @Resource
    private FindNowSemesterService findNowSemesterService;
    @Resource
    private FindIssueChannelService findIssueChannelService;
    @Resource
    private DownIssueChannelPayDetailService downIssueChannelPayDetailService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request,
                       @RequestParam("semesterId") long semesterId,
                       @RequestParam("icId") long icId,
                       @RequestParam(value = "type", required = false, defaultValue = "") String type){
        try{
            Semester semester = findNowSemesterService.get(semesterId);
            IssueChannel ic = findIssueChannelService.get(icId);
            String title = semester.getSemester2()+ic.getName();
            if("4".equals(type)){
                title += "统订教材应付款";
            }
            if("3".equals(type)){
                title += "自编教材应付款";
            }
            if("1".equals(type)){
                title += "自考教材应付款";
            }
            if(StringUtils.isEmpty(type)){
                title += "发书总码洋";
            }
            JSONObject jsonObject = findIssueChannelPayDetailService.findListByWhere(semesterId, icId, type);
            request.setAttribute("result", jsonObject);
            request.setAttribute("title", title);
            return "statis/findIssueChannelPayDetail";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计发行商应付款明细");
            return "error";
        }
    }

    @RequestMapping(value = "downDetail")
    @ResponseBody
    public String downDetail(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("semesterId") long semesterId,
                             @RequestParam("icId") long icId,
                             @RequestParam(value = "type", required = false, defaultValue = "") String type){
        try {
            Semester semester = findNowSemesterService.get(semesterId);
            IssueChannel ic = findIssueChannelService.get(icId);
            String title = semester.getSemester2()+ic.getName();
            if("4".equals(type)){
                title += "统订教材应付款";
            }
            if("3".equals(type)){
                title += "自编教材应付款";
            }
            if("1".equals(type)){
                title += "自考教材应付款";
            }
            if(StringUtils.isEmpty(type)){
                title += "发书总码洋";
            }
            String downUrl = "/excelDown/" + title + ".xls";
            downIssueChannelPayDetailService.down(semesterId, icId, type, title, request.getRealPath("") + downUrl);
            return downUrl;
        }catch(Exception e){
            super.outputException(request, e, log, "下载统计发行商应付款明细");
            return "error";
        }
    }
}
