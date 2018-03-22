package com.zs.web.controller.basic.issuerange;

import com.feinno.framework.common.dao.support.PageInfo;
import com.zs.domain.basic.IssueChannel;
import com.zs.domain.basic.IssueRange;
import com.zs.domain.sync.Province;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.basic.issuerange.FindIssueRangePageByWhereService;
import com.zs.service.sync.province.FindProvinceByCodeService;
import com.zs.service.sync.spot.FindSpotService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/findIssueRangePage")
public class FindIssueRangePageWhereController extends LoggerController {
    private static Logger log = Logger.getLogger(FindIssueRangePageWhereController.class);

    @Resource
    public FindIssueRangePageByWhereService findIssueRangePageByWhereService;
    @Resource
    public FindProvinceByCodeService findProvinceByCodeService;
    @Resource
    public FindSpotService findSpotService;
    @Resource
    public FindIssueChannelService findIssueChannelService;

    @RequestMapping(value = "findIssueRangePageByWhere")
    public String FindPageByWhere(@RequestParam(value="provCode", required=false, defaultValue="") String provCode,
                                  @RequestParam(value="spotCode", required=false, defaultValue="") String spotCode,
                                  @RequestParam(value="issueChannelId", required=false, defaultValue="") String issueChannelId,
                                  HttpServletRequest request) {
        try {
            //获取所有省中心
            List<Province> provinceList = findProvinceByCodeService.getAll();
            //获取所有学习中心
            List<Spot> spotList = findSpotService.getAll();
            //获取所有发行渠道
            List<IssueChannel> issueChannelList = findIssueChannelService.getAll();

            Map<String, String> params = new HashMap<String, String>();
            params.put("provCode", provCode);
            params.put("spotCode", spotCode);
            params.put("issueChannelId", issueChannelId);
            PageInfo<IssueRange> pageInfo = getPageInfo(request);
            Map<String, Boolean> sortMap = getSortMap(request);
            sortMap.put("s.code", true);
            pageInfo = findIssueRangePageByWhereService.findPageByWhere(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("provinceList", provinceList);
            request.setAttribute("spotList", spotList);
            request.setAttribute("issueChannelList", issueChannelList);
        } catch (Exception e) {
            super.outputException(request, e, log, "分页查询发行范围");
            return "error";
        }
        return "issueRange/rangeList";
    }
}
