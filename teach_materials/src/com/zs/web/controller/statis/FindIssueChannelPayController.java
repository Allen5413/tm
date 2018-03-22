package com.zs.web.controller.statis;

import com.zs.service.statis.FindIssueChannelPayService;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/9/29.
 */
@Controller
@RequestMapping(value = "/findIssueChannelPay")
public class FindIssueChannelPayController extends LoggerController {
    private static Logger log = Logger.getLogger(FindIssueChannelPayController.class);

    @Resource
    private FindIssueChannelPayService findIssueChannelPayService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request){
        try{
            JSONArray jsonArray = findIssueChannelPayService.findIssueChannelPay();
            request.setAttribute("result", jsonArray);
            return "statis/findIssueChannelPay";
        }
        catch(Exception e){
            super.outputException(request, e, log, "统计发行商应付款");
            return "error";
        }
    }
}
