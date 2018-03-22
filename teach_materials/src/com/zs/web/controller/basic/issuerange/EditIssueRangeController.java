package com.zs.web.controller.basic.issuerange;

import com.zs.domain.basic.IssueChannel;
import com.zs.domain.basic.IssueRange;
import com.zs.domain.sync.Spot;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.basic.issuerange.EditIssueRangeService;
import com.zs.service.basic.issuerange.FindIssueRangeBySpotCodeService;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.spot.FindSpotPageByWhereService;
import com.zs.tools.UserTools;
import com.zs.web.controller.LoggerController;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2015/5/4.
 */
@Controller
@RequestMapping(value = "/editIssueRange")
public class EditIssueRangeController extends
        LoggerController<IssueRange, EditIssueRangeService> {
    private static Logger log = Logger.getLogger(EditIssueRangeController.class);

    @Resource
    private EditIssueRangeService editIssueRangeService;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    public FindIssueChannelService findIssueChannelService;
    @Resource
    public FindIssueRangeBySpotCodeService findIssueRangeBySpotCodeService;

    @RequestMapping(value = "openEditIssueRangePage")
    public String openEditIssueRangePage(@RequestParam("spotCode") String spotCode, HttpServletRequest request){
        Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
        //获取学习中心对应的发行渠道
        IssueRange issueRange = findIssueRangeBySpotCodeService.getIssueRangeBySpotCode(spotCode);
        //获取所有发行渠道
        List<IssueChannel> issueChannelList = findIssueChannelService.getAll();
        request.setAttribute("spot", spot);
        request.setAttribute("issueRange", issueRange);
        request.setAttribute("issueChannelList", issueChannelList);
        return "issueRange/rangeEdit";
    }

    @RequestMapping(value = "issueRangeEdit")
    @ResponseBody
    public JSONObject editIssueRange(HttpServletRequest request, IssueRange issueRange){
        JSONObject jsonObject = new JSONObject();
        try{
            if(null != issueRange) {
                editIssueRangeService.editIssueRange(issueRange, UserTools.getLoginUserForName(request));
            }
            jsonObject.put("state", 0);
        }catch(Exception e){
            String msg = super.outputException(request, e, log, "修改发行范围");
            jsonObject.put("msg", msg);
            jsonObject.put("state", 1);
        }
        return jsonObject;
    }
}
