package com.zs.web.controller.basic.issuechannel;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.web.AbstractJQueryEntityController;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.FindIssueChannelPageByWhereService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/findIssueChannelPage")
public class FindIssueChannelPageByWhereController extends LoggerController<IssueChannel, FindIssueChannelPageByWhereService> {
    private static Logger log = Logger.getLogger(FindIssueChannelPageByWhereController.class);

    @Resource
    public FindIssueChannelPageByWhereService findIssueChannelPageByWhereService;

    @RequestMapping(value = "findIssueChannelPageByWhere")
    public String FindPageByWhere(@RequestParam(value="name", required=false, defaultValue="") String name,
                                  @RequestParam(value="code", required=false, defaultValue="") String code,
                                  HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name.trim());
            params.put("code", code.trim());
            PageInfo<IssueChannel> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("operateTime", false);
            pageInfo = findIssueChannelPageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询发行渠道信息");
            return "error";
        }
        return "issueChannel/channelList";
    }
}
