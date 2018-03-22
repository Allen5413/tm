package com.zs.web.controller.basic.issuerange;

import com.zs.domain.basic.IssueRange;
import com.zs.service.basic.issuerange.FindIssueRangeService;
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
@RequestMapping(value = "/findIssueRange")
public class FindIssueRangeByIdController extends
        LoggerController<IssueRange, FindIssueRangeService> {
    private static Logger log = Logger.getLogger(FindIssueRangeByIdController.class);

    @Resource
    private FindIssueRangeService findIssueRangeService;

    @RequestMapping(value = "findIssueRangeByID")
    public String findIssueRangeByID(@RequestParam("id") long id, HttpServletRequest request){
        try{
            IssueRange issueRange = findIssueRangeService.get(id);
            request.setAttribute("issueRange", issueRange);
        }catch(Exception e){
            super.outputException(request, e, log, "查询发行范围详情");
        }
        return "user/userAdd";
    }
}
