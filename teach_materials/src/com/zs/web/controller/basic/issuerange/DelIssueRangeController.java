package com.zs.web.controller.basic.issuerange;

import com.zs.domain.basic.IssueRange;
import com.zs.service.basic.issuerange.DelIssueRangeService;
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
@RequestMapping(value = "/delIssueRange")
public class DelIssueRangeController extends
        LoggerController<IssueRange, DelIssueRangeService> {
    private static Logger log = Logger.getLogger(DelIssueRangeController.class);

    @Resource
    private DelIssueRangeService delIssueRangeService;

    @RequestMapping(value = "issueRangeDel")
    public String delIssueRange(@RequestParam("id") long id, HttpServletRequest request){
        try{
            delIssueRangeService.delIssueRange(id);
            return "welcome";
        } catch (Exception e){
            super.outputException(request, e, log, "删除发行范围");
            return "error";
        }
    }
}
