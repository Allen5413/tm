package com.zs.web.controller.basic.issuechannel;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.web.AbstractJQueryEntityController;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.DelIssueChannelService;
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
@RequestMapping(value = "/delIssueChannel")
public class DelIssueChannelController extends
        LoggerController<IssueChannel, DelIssueChannelService> {
    private static Logger log = Logger.getLogger(DelIssueChannelController.class);

    @Resource
    private DelIssueChannelService delIssueChannelService;

    @RequestMapping(value = "issueChannelDel")
    public String delIssueChannel(@RequestParam("id") long id, HttpServletRequest request){
        try{
            delIssueChannelService.delIssueChannel(id);
            return "welcome";
        } catch (Exception e){
            super.outputException(request, e, log, "删除发行渠道");
            return "error";
        }
    }
}
