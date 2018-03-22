package com.zs.web.controller.basic.issuechannel;

import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/findIssueChannel")
public class FindIssueChannelByIdController extends
        LoggerController<IssueChannel, FindIssueChannelService> {
    private static Logger log = Logger.getLogger(FindIssueChannelByIdController.class);

    @Resource
    private FindIssueChannelService findIssueChannelService;

    @RequestMapping(value = "findIssueChannelByID")
    public String findIssueChannelByID(@RequestParam("id") long id, HttpServletRequest request){
        try{
            IssueChannel issueChannel = findIssueChannelService.get(id);
            request.setAttribute("issueChannel", issueChannel);
        }catch(Exception e){
            super.outputException(request, e, log, "查询发行渠道详情");
        }
        return "user/userAdd";
    }
}
